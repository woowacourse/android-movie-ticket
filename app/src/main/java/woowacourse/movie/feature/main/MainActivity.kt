package woowacourse.movie.feature.main

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.MockMovieRepository
import woowacourse.movie.feature.main.ui.ListViewAdapter
import woowacourse.movie.feature.main.ui.MovieModel
import woowacourse.movie.feature.reservation.ReservationActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private val movieListView: ListView by lazy {
        findViewById(R.id.list_view)
    }

    private val presenter = MainPresenter(this, MockMovieRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.fetchMovieList()
    }

    override fun displayMovies(movies: List<MovieModel>) {
        movieListView.adapter =
            ListViewAdapter(movies) { position ->
                presenter.selectMovie(movies[position].id)
            }
    }

    override fun navigateToReservationScreen(id: Long) {
        startActivity(ReservationActivity.getIntent(this, id))
    }
}
