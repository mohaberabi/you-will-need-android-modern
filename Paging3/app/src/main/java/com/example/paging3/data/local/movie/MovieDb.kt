import MovieDao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paging3.data.local.movie.MovieEntity


@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class MovieDb : RoomDatabase() {

    abstract val movieDao: MovieDao

}