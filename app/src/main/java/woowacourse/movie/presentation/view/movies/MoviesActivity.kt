package woowacourse.movie.presentation.view.movies

import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.view.reservation.detail.ReservationDetailActivity

class MoviesActivity :
    BaseActivity(R.layout.activity_movies),
    MoviesContract.View {
    private val presenter: MoviesPresenter by lazy { MoviesPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.fetchData()
    }

    override fun setScreen(
        movies: List<Movie>,
        navigateToReservationScreen: (Movie) -> Unit,
    ) {
        val lvMovie = findViewById<ListView>(R.id.lv_movie)
        lvMovie.adapter =
            MovieListAdapter(
                movies,
                object : OnMovieEventListener {
                    override fun onClick(movie: Movie) {
                        navigateToReservationScreen(movie)
                    }
                },
            )
    }

    override fun navigateToReservationScreen(movie: Movie) {
        val intent = ReservationDetailActivity.newIntent(this, movie)
        startActivity(intent)
    }
}
