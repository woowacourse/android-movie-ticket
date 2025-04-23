package woowacourse.movie.view.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.movie.MoviePresenter
import woowacourse.movie.view.Extras
import woowacourse.movie.view.reservation.ReservationActivity

class MoviesActivity :
    AppCompatActivity(),
    MovieContract.View {
    private val presenter: MoviePresenter by lazy { MoviePresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.fetchMovies()
    }

    override fun updateView(movies: List<Movie>) {
        setupMovieAdapter(movies)
    }

    private fun setupMovieAdapter(movies: List<Movie>) {
        val movieListView = findViewById<ListView>(R.id.lv_movies)
        movieListView.adapter =
            MovieAdapter(
                movies,
                object : MovieClickListener {
                    override fun onReservationClick(movie: Movie) {
                        navigateToReservationComplete(movie)
                    }
                },
            )
    }

    private fun navigateToReservationComplete(movie: Movie) {
        val intent =
            Intent(
                this,
                ReservationActivity::class.java,
            ).apply { putExtra(Extras.MovieData.MOVIE_KEY, movie) }
        startActivity(intent)
    }
}
