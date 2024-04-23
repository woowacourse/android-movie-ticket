package woowacourse.movie.view.home

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieCatalogAdapter
import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.presenter.home.ReservationHomeContract
import woowacourse.movie.presenter.home.ReservationHomePresenter
import woowacourse.movie.view.detail.ReservationDetailActivity

class ReservationHomeActivity : AppCompatActivity(), ReservationHomeContract.View {
    private val presenter = ReservationHomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_home)

        val movieCatalogAdapter =
            MovieCatalogAdapter(this, ScreeningDao().findAll()) { movie ->
                presenter.loadMovie(movie)
            }
        findViewById<ListView>(R.id.list_view_reservation_home).apply {
            adapter = movieCatalogAdapter
        }
    }

    override fun navigateToDetail(movieId: Int) {
        val intent = Intent(this, ReservationDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, movieId)
        startActivity(intent)
    }

    companion object {
        const val MOVIE_ID = "movieId"
    }
}
