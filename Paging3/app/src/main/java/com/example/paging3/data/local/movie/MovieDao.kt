
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.paging3.data.local.movie.MovieEntity

@Dao
interface MovieDao {


    @Upsert

    suspend fun upsertMovieList(movies: List<MovieEntity>)


    @Query("DELETE FROM movies")

    suspend fun deleteAll()


    @Query("SELECT * FROM movies ")
     fun getMoviesByCategory(category: String): PagingSource<Int , MovieEntity>

}