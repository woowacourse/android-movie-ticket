package woowacourse.movie.presentation.screening

import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.FakeMovieRepository
import woowacourse.movie.presentation.reservation.booking.MovieReservationActivity

class ScreeningMovieActivity : AppCompatActivity(), ScreeningMovieView {
    private lateinit var presenter: ScreeningMoviePresenter
    private lateinit var moviesView: ListView
    private lateinit var adapter: ScreeningMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_movie)
        initViews()
        presenter = ScreeningMoviePresenter(this, FakeMovieRepository)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun updateMovies(movies: List<ScreeningMovieUiModel>) {
        adapter.updateMovies(movies)
    }

    override fun navigateToReservationView(movieId: Long) {
        val intent = MovieReservationActivity.newIntent(this, movieId)
        startActivity(intent)
    }

    private fun initViews() {
        moviesView = findViewById<ListView>(R.id.list_screening_movie)
        adapter =
            ScreeningMovieAdapter { id -> presenter.startReservation(id) }
                .also { moviesView.adapter = it }
    }
}
