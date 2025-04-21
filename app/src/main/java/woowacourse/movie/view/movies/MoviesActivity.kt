package woowacourse.movie.view.movies

import android.content.Intent
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
        val movies = List(3000) { dummyMovie }
        lvMovie.adapter =
            MovieListAdapter(
                movies,
                object : OnMovieEventListener {
                    override fun onReserveButtonClick(movie: Movie) {
                        val intent =
                            Intent(this@MoviesActivity, ReservationActivity::class.java).putExtra(
                                ReservationActivity.BUNDLE_KEY_MOVIE,
                                movie,
                            )
                        startActivity(intent)
                    }
                },
            )
    }

    companion object {
        private val dummyMovie =
            Movie(
                R.drawable.harrypotter.toString(),
                "해리 포터와 마법사의 돌",
                ScreeningPeriod(
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                ),
                152,
            )
    }
}
