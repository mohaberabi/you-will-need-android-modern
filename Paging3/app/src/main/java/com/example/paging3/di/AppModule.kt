import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.jetmovie.listing.data.local.movie.MovieDb
import com.example.paging3.data.api.MovieApi
import com.example.paging3.data.api.MovieRemoteMediator
import com.example.paging3.data.local.movie.MovieEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder().baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                client
            ).build().create(MovieApi::class.java)
    }


    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDb {
        return Room.databaseBuilder(app, MovieDb::class.java, "movie.db").build()
    }


    @Provides
    @Singleton
    fun provideMovieDao(movieDb: MovieDb): MovieDao = movieDb.movieDao


    @OptIn(ExperimentalPagingApi::class)
    fun provideMoviePager(movieDb: MovieDb, movieApi: MovieApi): Pager<Int, MovieEntity> {
        return Pager(
            config = PagingConfig(20),
            remoteMediator = MovieRemoteMediator(movieDb, movieApi), pagingSourceFactory = {
                movieDb.movieDao.getMoviesByCategory("popular")
            }
        )
    }
}
