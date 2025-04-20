package woowacourse.movie.view.movies

import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.fixture.dummyMovie
import woowacourse.movie.view.reservation.ReservationActivity

class MoviesActivity : BaseActivity(R.layout.activity_movies) {
    override fun setupViews(savedInstanceState: Bundle?) {
        setMovieListView()
    }

    private fun setMovieListView() {
        val lvMovie = findViewById<ListView>(R.id.lv_movie)
        val movies = listOf(dummyMovie)
        lvMovie.adapter =
            MovieListAdapter(
                movies,
                object : OnMovieEventListener {
                    override fun onClick(movie: Movie) {
                        navigateToReservation(movie)
                    }
                },
            )
    }

    private fun navigateToReservation(movie: Movie) {
        val intent = ReservationActivity.newIntent(this, movie)
        startActivity(intent)
    }
}
