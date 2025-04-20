package woowacourse.movie.view.movies

import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningPeriod
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.reservation.ReservationActivity
import java.time.LocalDate

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

    companion object {
        private val dummyMovie =
            Movie(
                "해리 포터와 마법사의 돌",
                R.drawable.harrypotter.toString(),
                ScreeningPeriod(
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                ),
                152,
            )
    }
}
