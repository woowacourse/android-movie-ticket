package woowacourse.movie.presentation.view.movies

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.fetchData()
    }

    override fun showScreen(movies: List<MovieUiModel>) {
        setMovies(movies)
    }

    private fun setMovies(movies: List<MovieUiModel>) {
        val lvMovie = findViewById<RecyclerView>(R.id.lv_movie)
        val adapter =
            MoviesAdapter(
                object : OnMovieEventListener {
                    override fun onClick(movie: MovieUiModel) {
                        navigateToReservationScreen(movie)
                    }
                },
            )
        lvMovie.layoutManager = LinearLayoutManager(this)
        lvMovie.adapter = adapter
        adapter.submitList(movies)
    }

    private fun navigateToReservationScreen(movie: MovieUiModel) {
        val intent = ReservationDetailActivity.newIntent(this, movie)
        startActivity(intent)
    }
}
