package woowacourse.movie

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import domain.Movie
import woowacourse.movie.adapter.MoviesAdapter
import java.time.LocalDate

class MoviesActivity : AppCompatActivity() {

    private val moviesListView: ListView by lazy { findViewById(R.id.movies_list_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        initMoviesListView()
    }

    private fun initMoviesListView() {
        val movies: List<Movie> = listOf(
            Movie(
                name = "해리포터",
                posterImage = null,
                screeningDate = LocalDate.of(2000, 10, 1),
                runningTime = 120,
                description = "마법영화"
            )
        )
        moviesListView.adapter = MoviesAdapter(this, movies)
    }
}
