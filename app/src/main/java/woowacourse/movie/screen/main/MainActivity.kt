package woowacourse.movie.screen.main

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.screen.reservation.ReservationActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private val movieListView: ListView by lazy {
        findViewById(R.id.list_view)
    }

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onStart()
    }

    override fun displayMovies(movies: List<MovieModel>) {
        movieListView.adapter =
            ListViewAdapter(movies) { position ->
                presenter.onMovieSelected(movies[position].id)
            }
    }

    override fun navigateToReservation(id: Long) {
        startActivity(ReservationActivity.getIntent(this, id))
    }
}
