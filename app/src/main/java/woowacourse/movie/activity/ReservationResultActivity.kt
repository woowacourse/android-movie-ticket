package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Reservation
import woowacourse.movie.dto.ReservationDto
import woowacourse.movie.dto.ReservationDtoConverter
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.ReservationDetailView

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val reservation = getReservationData()
        if (reservation != null) {
            MovieView(
                this, reservation.movie, title = findViewById(R.id.movie_reservation_result_title)
            ).render()

            ReservationDetailView(
                this,
                reservation.detail,
                findViewById(R.id.movie_reservation_result_date),
                findViewById(R.id.movie_reservation_result_people_count),
                findViewById(R.id.movie_reservation_result_price),
            ).render()
        }
    }

    private fun getReservationData(): Reservation? {
        val reservationDto =
            intent.extras?.getSerializableCompat<ReservationDto>(RESERVATION_KEY_VALUE)
        return reservationDto?.let { ReservationDtoConverter().convertDtoToModel(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val RESERVATION_KEY_VALUE = "reservation"
        fun start(context: Context, reservation: Reservation) {
            val intent = Intent(context, ReservationResultActivity::class.java)
            val reservationDto = ReservationDtoConverter().convertModelToDto(reservation)
            intent.putExtra(RESERVATION_KEY_VALUE, reservationDto)
            context.startActivity(intent)
        }
    }
}
