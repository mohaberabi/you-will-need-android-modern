package com.example.paging3.data.api

import android.net.http.HttpException
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.jetmovie.listing.data.local.movie.MovieDb
import com.example.paging3.data.local.movie.MovieEntity
import com.example.paging3.data.mapper.toMovieEntity
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator (
    private val movieDb: MovieDb,
    private val movieApi: MovieApi,

): RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {

        return  try {
            val loadKey = when(loadType){
                LoadType.REFRESH->1
                LoadType.APPEND->{
                    val last = state.lastItemOrNull()
                    if (last==null){
                         1
                    }else {
                        if (last.id==null){
                            1
                        }else {
                            (  (last.id/state.config.pageSize)+1)

                        }
                    }
                }
                LoadType.PREPEND->return  MediatorResult.Success(endOfPaginationReached = true)
            }

            val movies = movieApi.getMoviesList("popular",loadKey)
            movieDb.withTransaction {

                if (loadType==LoadType.REFRESH){
                    movieDb.movieDao.deleteAll()
                }

                val moviesEntity = movies.map { it.toMovieEntity("popular") }

                movieDb.movieDao.upsertMovieList(moviesEntity)
            }

            MediatorResult.Success(endOfPaginationReached = movies.isEmpty())
        } catch (e:IOException){
            MediatorResult.Error(e)

        }catch (e:retrofit2.HttpException){
            MediatorResult.Error(e)

        }



    }

}
