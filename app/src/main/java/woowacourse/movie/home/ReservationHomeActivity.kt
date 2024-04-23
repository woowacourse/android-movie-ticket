package woowacourse.movie.home

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieCatalogAdapter
import woowacourse.movie.reservation.detail.ReservationDetailActivity

class ReservationHomeActivity : AppCompatActivity(), ReservationHomeContract.View {
    private val movies: ListView by lazy { findViewById(R.id.list_view_reservation_home) }
    private val reservationHomePresenter = ReservationHomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_home)

        movies.adapter =
            MovieCatalogAdapter(this, reservationHomePresenter.obtainMovies()) { movie ->
                reservationHomePresenter.deliverMovie(movie.id)
            }
    }

    override fun moveToReservationDetail(movieId: Int) {
        startActivity(ReservationDetailActivity.getIntent(this, movieId))
    }
}
