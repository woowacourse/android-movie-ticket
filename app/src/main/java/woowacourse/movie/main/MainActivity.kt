package woowacourse.movie.main

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.ListViewAdapter
import woowacourse.movie.model.MovieRepository
import woowacourse.movie.model.UiMovie
import woowacourse.movie.reservation.ReservationActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private val mainPresenter = MainPresenter(this, MovieRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter.onViewCreated()
    }

    override fun displayMovies(uiMovies: List<UiMovie>) {
        val movieListView = findViewById<ListView>(R.id.list_view)
        movieListView.adapter =
            ListViewAdapter(uiMovies) { position ->
                mainPresenter.onMovieSelected(position)
            }
    }

    override fun navigateToReservation(uiMovie: UiMovie) {
        Intent(this, ReservationActivity::class.java).apply {
            putExtra(EXTRA_MOVIE, uiMovie)
            startActivity(this)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "movie"
    }
}
