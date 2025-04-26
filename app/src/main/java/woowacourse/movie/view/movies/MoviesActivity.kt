package woowacourse.movie.view.movies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private val movieListView by lazy { findViewById<RecyclerView>(R.id.rv_main_movies) }

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
                    movies = mutableListOf(),
                    movieClickListener =
                        object : MovieClickListener {
                            override fun onReservationClick(movieId: Long) {
                                presenter.createMovie(movieId)
                            }
                        },
                    advertisementClickListener = { presenter.clickAdvertisement(ADVERTISEMENT_URL) },
                )
            movieListView.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(this@MoviesActivity)
            }
        }
        movieAdapter.updateMovies(movies)
    }

    override fun showReservationView(movie: Movie) {
        startActivity(ReservationActivity.getIntent(this, movie))
    }

    override fun showAdvertisement(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }

    companion object {
        private const val ADVERTISEMENT_URL = "https://www.woowacourse.io/"

        fun getIntent(context: Context): Intent =
            Intent(context, MoviesActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }
    }
}
