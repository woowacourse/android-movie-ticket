package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.data.StubMovieRepository
import woowacourse.movie.model.ScreenMovieUiModel
import woowacourse.movie.presenter.ScreenMoviePresenter
import woowacourse.movie.view.ScreeningMovieView

class ScreeningMovieActivity : AppCompatActivity(), ScreeningMovieView {
    private lateinit var presenter: ScreenMoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = ScreenMoviePresenter(this, StubMovieRepository)
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
        Log.d(
            "로그",
            "ScreeningMovieActivity - navigateToMovieReservationView() called $screenMovieId",
        )
        val intent =
            Intent(this, MovieReservationActivity::class.java).apply {
                putExtra("screenMovieId", screenMovieId)
            }
        startActivity(intent)
    }
}
