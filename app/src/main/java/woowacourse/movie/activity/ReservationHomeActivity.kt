package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieCatalogAdapter
import woowacourse.movie.presenter.ReservationHomePresenter
import woowacourse.movie.view.ReservationHomeContract

class ReservationHomeActivity : AppCompatActivity(), ReservationHomeContract {
    private val movies: ListView by lazy { findViewById(R.id.list_view_reservation_home) }
    private val reservationHomePresenter = ReservationHomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_home)

        movies.adapter = MovieCatalogAdapter(this, reservationHomePresenter.movies) { movie ->
            reservationHomePresenter.deliverMovie(movie.id)
        }
    }

    override fun moveToReservationDetail(movieId: Int) {
        val intent = Intent(this, ReservationDetailActivity::class.java)
        intent.putExtra("movieId", movieId)
        startActivity(intent)
    }
}
