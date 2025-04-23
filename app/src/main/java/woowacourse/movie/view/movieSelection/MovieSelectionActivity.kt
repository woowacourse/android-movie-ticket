package woowacourse.movie.view.movieSelection

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.model.MovieUiModel
import woowacourse.movie.view.model.toUiModel
import woowacourse.movie.view.movieReservation.MovieReservationActivity
import java.time.LocalDate

class MovieSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()

        val movies: List<MovieUiModel> =
            (1..10000).map { n ->
                Movie(
                    "해리 포터 $n",
                    startDate = LocalDate.of(2025, 4, 1),
                    endDate = LocalDate.of(2025, 4, 25),
                    runningTime = 152,
                ).toUiModel()
            }

        val movieListView = findViewById<ListView>(R.id.movie_list)
        val movieAdapter = MovieAdapter(movies) { movie -> onReserveMovie(movie) }
        movieListView.adapter = movieAdapter
    }

    private fun initializeView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun onReserveMovie(movie: MovieUiModel) {
        val intent = MovieReservationActivity.createIntent(this, movie)
        startActivity(intent)
    }
}
