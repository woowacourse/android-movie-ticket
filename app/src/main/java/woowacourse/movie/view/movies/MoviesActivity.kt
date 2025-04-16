package woowacourse.movie.view.movies

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.reservation.result.ReservationResultActivity

class MoviesActivity : BaseActivity() {
    override fun initView() {
        setMovieListView()
    }

    private fun setMovieListView() {
        val lvMovie = findViewById<ListView>(R.id.lv_movie)
        val movies =
            listOf(
                Movie(
                    R.drawable.harrypotter,
                    "해리 포터와 마법사의 돌",
                    getString(R.string.movie_date, "2025.4.1"),
                    getString(R.string.running_time, "152"),
                ),
            )
        val movieListAdapter =
            MovieListAdapter(
                movies,
                object : OnMovieEventListener {
                    override fun onClick(movie: Movie) {
                        val bundle = Bundle()
                        movie.let {
                            bundle.putString(getString(R.string.bundle_key_movie_title), it.title)
                            bundle.putString(getString(R.string.bundle_key_movie_date), it.date)
                        }

                        startActivity(
                            Intent(this@MoviesActivity, ReservationResultActivity::class.java).putExtras(bundle),
                        )
                    }
                },
            )
        lvMovie.adapter = movieListAdapter
    }
}
