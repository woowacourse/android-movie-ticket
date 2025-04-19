package woowacourse.movie.presentation.movies

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Movie.Companion.movies
import woowacourse.movie.presentation.bookingdetail.BookingDetailActivity
import woowacourse.movie.presentation.movies.adapter.MoviesAdapter

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
        val moviesAdapter = MoviesAdapter(this, movies) { movie -> bookMovie(movie) }
        findViewById<ListView>(R.id.lv_movies).adapter = moviesAdapter
    }

    private fun bookMovie(movie: Movie) {
        val intent =
            BookingDetailActivity.newIntent(
                context = this,
                movie = movie,
            )
        startActivity(intent)
    }
}
