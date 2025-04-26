package woowacourse.movie.view.movies

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.presenter.movies.MoviesContracts
import woowacourse.movie.presenter.movies.MoviesPresenter
import woowacourse.movie.view.reservation.ReservationActivity

class MoviesActivity :
    AppCompatActivity(),
    MoviesContracts.View {
    private val presenter: MoviesContracts.Presenter = MoviesPresenter(this)
    private lateinit var movieAdapter: MovieAdapter
    private val movieListView by lazy { findViewById<ListView>(R.id.lv_main_movies) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        presenter.initView()
    }

    override fun showMovies(movies: List<Movie>) {
        if (::movieAdapter.isInitialized.not()) {
            movieAdapter =
                MovieAdapter(
                    context = this,
                    movies = mutableListOf(),
                    movieClickListener =
                        object : MovieClickListener {
                            override fun onReservationClick(movieId: Long) {
                                presenter.createMovie(movieId)
                            }
                        },
                )
            movieListView.adapter = movieAdapter
        }
        movieAdapter.updateMovies(movies)
    }

    override fun showReservationView(movie: Movie) {
        startActivity(ReservationActivity.getIntent(this, movie))
    }
}
