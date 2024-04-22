package woowacourse.movie.presentation.screen

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.db.MovieDao
import woowacourse.movie.presentation.reservation.booking.ReservationActivity
import woowacourse.movie.presentation.screen.adapter.MovieListAdapter

class ScreeningMovieActivity : AppCompatActivity(), ScreeningMovieContract.View {
    private val presenter: ScreeningMoviePresenter = ScreeningMoviePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_movie)

        val movieAdapter =
            MovieListAdapter(
                this,
                MovieDao().findAll(),
            ) { movie ->
                presenter.registerMovie(movie)
            }

        val listView = findViewById<ListView>(R.id.movie_list)
        listView.apply { adapter = movieAdapter }
    }

    override fun startNextActivity(movieId: Int) {
        val intent = Intent(this, ReservationActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    companion object {
        const val MOVIE_ID = "movieId"
    }
}
