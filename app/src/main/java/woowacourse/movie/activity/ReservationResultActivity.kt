package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import domain.Reservation
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.ReservationDetailView
import woowacourse.movie.view.model.ReservationDomainViewMapper
import woowacourse.movie.view.model.ReservationViewModel

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservationViewModel = getReservationModelView()
        if (reservationViewModel == null) finishActivity()
        else {
            renderMovieView(reservationViewModel)
            renderReservationDetailView(reservationViewModel)
        }
    }

    private fun finishActivity() {
        Toast.makeText(this, RESERVATION_DATA_NULL_ERROR, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun renderMovieView(reservationDto: ReservationViewModel) {
        MovieView(
            title = findViewById(R.id.movie_reservation_result_title)
        ).render(reservationDto.movie)
    }

    private fun renderReservationDetailView(reservationDto: ReservationViewModel) {
        ReservationDetailView(
            date = findViewById(R.id.movie_reservation_result_date),
            peopleCount = findViewById(R.id.movie_reservation_result_people_count),
            price = findViewById(R.id.movie_reservation_result_price),
        ).render(reservationDto.detail)
    }

    private fun getReservationModelView(): ReservationViewModel? {
        return intent.extras?.getSerializableCompat<ReservationViewModel>(RESERVATION_KEY_VALUE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val RESERVATION_KEY_VALUE = "reservation"
        private const val RESERVATION_DATA_NULL_ERROR = "예약 정보를 받지 못하였습니다!"
        fun start(context: Context, reservation: domain.Reservation) {
            val intent = Intent(context, ReservationResultActivity::class.java)
            val reservationDto = ReservationDomainViewMapper().toView(reservation)
            intent.putExtra(RESERVATION_KEY_VALUE, reservationDto)
            context.startActivity(intent)
        }
    }
}
