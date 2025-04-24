package woowacourse.movie.view.movies

import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.reservation.ReservationActivity

class MoviesActivity :
    BaseActivity(R.layout.activity_movies),
    MoviesContract.View {
    private val presenter = MoviesPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadData()
    }

    override fun showMovies(movies: List<Movie>) {
        val lvMovie = findViewById<ListView>(R.id.lv_movie)
        lvMovie.adapter =
            MovieListAdapter(
                movies,
                object : OnMovieEventListener {
                    override fun onReserveButtonClick(movie: Movie) {
                        val intent = ReservationActivity.newIntent(this@MoviesActivity, movie)
                        startActivity(intent)
                    }
                },
            )
    }
}
