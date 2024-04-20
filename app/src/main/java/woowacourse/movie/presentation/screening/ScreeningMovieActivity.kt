package woowacourse.movie.presentation.screening

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.StubMovieRepository
import woowacourse.movie.presentation.reservation.booking.MovieReservationActivity

class ScreeningMovieActivity : AppCompatActivity(), ScreeningMovieView {
    private lateinit var presenter: ScreeningMoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_movie)
        presenter = ScreeningMoviePresenter(this, StubMovieRepository)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun showMovies(movies: List<ScreeningMovieUiModel>) {
        val listView = findViewById<ListView>(R.id.list_screening_movie)
        listView.adapter =
            ScreeningMovieAdapter(this, movies) { presenter.startReservation(it) }
    }

    override fun onClickReservationButton(screenMovieId: Long) {
        val intent =
            Intent(this, MovieReservationActivity::class.java).apply {
                putExtra(MovieReservationActivity.EXTRA_SCREEN_MOVIE_ID, screenMovieId)
            }
        startActivity(intent)
    }
}
