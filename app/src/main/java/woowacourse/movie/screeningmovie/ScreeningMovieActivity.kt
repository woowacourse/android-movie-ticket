package woowacourse.movie.screeningmovie

import android.os.Bundle
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.data.DummyMovies
import woowacourse.movie.moviereservation.MovieReservationActivity

class ScreeningMovieActivity : AppCompatActivity(), ScreeningMovieView {
    private lateinit var presenter: ScreenMoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screening_movie)
        presenter = ScreenMoviePresenter(this, DummyMovies)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun showMovies(movies: List<ScreenMovieUiModel>) {
        val listView = findViewById<ListView>(R.id.list_view)
        listView.adapter =
            MovieAdapter(this, movies) { presenter.startReservation(it) }
    }

    override fun onClickReservationButton(screenMovieId: Long) {
        startActivity(MovieReservationActivity.getIntent(this, screenMovieId))
    }
}
