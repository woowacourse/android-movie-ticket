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

        val reservationDto = getReservationDto()
        if (reservationDto != null) {
            renderMovieView(reservationDto)
            renderReservationDetailView(reservationDto)
        }
    }

    private fun renderMovieView(reservationDto: ReservationDto) {
        MovieView(
            this,
            reservationDto.movie,
            title = findViewById(R.id.movie_reservation_result_title)
        ).render()
    }

    private fun renderReservationDetailView(reservationDto: ReservationDto) {
        ReservationDetailView(
            this,
            reservationDto.detail,
            findViewById(R.id.movie_reservation_result_date),
            findViewById(R.id.movie_reservation_result_people_count),
            findViewById(R.id.movie_reservation_result_price),
        ).render()
    }

    private fun getReservationDto(): ReservationDto? {
        return intent.extras?.getSerializableCompat<ReservationDto>(RESERVATION_KEY_VALUE)
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
