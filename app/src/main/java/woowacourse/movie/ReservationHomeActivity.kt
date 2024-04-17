package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import presenter.ReservationHomePresenter
import view.ReservationHomeContract

class ReservationHomeActivity : AppCompatActivity(), ReservationHomeContract {
    private val reservationHomePresenter = ReservationHomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_home)

        val movieCatalogAdapter =
            MovieCatalogAdapter(this, reservationHomePresenter.movies) { movie ->
                reservationHomePresenter.deliverMovie(movie.id)
            }
        findViewById<ListView>(R.id.list_view_reservation_home).apply {
            adapter = movieCatalogAdapter
        }
    }

    override fun moveToReservationDetail(movieId: Int) {
        val intent = Intent(this, ReservationDetailActivity::class.java)
        intent.putExtra("movieId", movieId)
        startActivity(intent)
    }
}
