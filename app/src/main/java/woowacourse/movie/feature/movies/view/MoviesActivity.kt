package woowacourse.movie.feature.movies.view

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie.Companion.movies
import woowacourse.movie.feature.bookingdetail.view.BookingDetailActivity
import woowacourse.movie.feature.mapper.toUi
import woowacourse.movie.feature.model.MovieUiModel
import woowacourse.movie.feature.movies.view.adapter.MoviesAdapter

class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupMovies()
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_movies)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupMovies() {
        val moviesAdapter = MoviesAdapter(movies.map { it.toUi() }) { movie -> bookMovie(movie) }
        findViewById<ListView>(R.id.lv_movies).adapter = moviesAdapter
    }

    private fun bookMovie(movie: MovieUiModel) {
        val intent =
            BookingDetailActivity.newIntent(
                context = this,
                movie = movie,
            )
        startActivity(intent)
    }
}
