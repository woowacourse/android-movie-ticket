package woowacourse.movie.view.movies

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.reservation.ReservationActivity

class MoviesActivity :
    BaseActivity(R.layout.activity_movies),
    MoviesContract.View {
    private val presenter = MoviesPresenter(this)

    private lateinit var rvMovie: RecyclerView
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()

        presenter.loadData(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun setupViews() {
        rvMovie = findViewById(R.id.rv_movie)

        movieListAdapter =
            MovieListAdapter(
                object : OnMovieEventListener {
                    override fun onReserveButtonClick(movie: Movie) {
                        val intent = ReservationActivity.newIntent(this@MoviesActivity, movie)
                        startActivity(intent)
                    }
                },
            )
        rvMovie.adapter = movieListAdapter

        rvMovie.layoutManager = LinearLayoutManager(this)
    }

    override fun showMovies(movies: List<MovieListItem>) {
        movieListAdapter.updateItems(movies)
    }
}
