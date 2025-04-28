package woowacourse.movie.presentation.view.movies

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.model.MovieUiModel
import woowacourse.movie.presentation.view.movies.adapter.MoviesAdapter
import woowacourse.movie.presentation.view.movies.adapter.OnMovieEventListener
import woowacourse.movie.presentation.view.reservation.detail.ReservationDetailActivity

class MoviesActivity :
    BaseActivity(R.layout.activity_movies),
    MoviesContract.View {
    private val presenter: MoviesPresenter by lazy { MoviesPresenter(this) }
    private val moviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter(
            object : OnMovieEventListener {
                override fun onClick(movie: MovieUiModel) {
                    navigateToReservationScreen(movie)
                }
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.fetchData()
    }

    override fun showScreen(movies: List<MovieUiModel>) {
        setMoviesAdapter()
        updateMovies(movies)
    }

    private fun setMoviesAdapter() {
        val lvMovie = findViewById<RecyclerView>(R.id.lv_movie)
        lvMovie.adapter = moviesAdapter
    }

    private fun updateMovies(movies: List<MovieUiModel>) {
        moviesAdapter.submitList(movies)
    }

    private fun navigateToReservationScreen(movie: MovieUiModel) {
        val intent = ReservationDetailActivity.newIntent(this, movie)
        startActivity(intent)
    }
}
