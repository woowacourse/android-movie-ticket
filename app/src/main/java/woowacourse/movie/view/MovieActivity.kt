package woowacourse.movie.view

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.adpater.MovieListAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.MovieBookingActivity.Companion.movieBookingIntent
import woowacourse.movie.Movies.View
import woowacourse.movie.presenter.MoviesPresenter

class MovieActivity : AppCompatActivity(), View {
    private lateinit var presenter: MoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter = MoviesPresenter(this@MovieActivity)
        presenter.loadMovies()
    }

    override fun showMovies(movies: List<Movie>) {
        val movieListView: ListView = findViewById(R.id.movies)
        movieListView.adapter = MovieListAdapter(movies) { movie ->
            presenter.selectedMovie(movie)
        }
    }

    override fun navigateToBook(movie: Movie) {
        val intent = movieBookingIntent(this@MovieActivity, movie)
        startActivity(intent)
    }
}
