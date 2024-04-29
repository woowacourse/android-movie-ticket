package woowacourse.movie.screeningmovie

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.DummyMovies
import woowacourse.movie.moviereservation.MovieReservationActivity

class ScreeningMovieActivity : AppCompatActivity(), ScreeningMovieContract.View {
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
        val listView = findViewById<RecyclerView>(R.id.rcv_screening_movie)
        listView.adapter =
            MovieAdapter(insertAdvertisements(movies, 3)) { presenter.startReservation(it) }
    }

    override fun onClickReservationButton(screeningMovieId: Long) {
        startActivity(MovieReservationActivity.getIntent(this, screeningMovieId))
    }
}
