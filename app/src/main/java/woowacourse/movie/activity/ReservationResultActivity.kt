package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Reservation
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

        val reservationDto = getReservationDto()
        if (reservationDto != null) {
            renderMovieView(reservationDto)
            renderReservationDetailView(reservationDto)
        }
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

    private fun getReservationDto(): ReservationViewModel? {
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
        fun start(context: Context, reservation: Reservation) {
            val intent = Intent(context, ReservationResultActivity::class.java)
            val reservationDto = ReservationDomainViewMapper().toView(reservation)
            intent.putExtra(RESERVATION_KEY_VALUE, reservationDto)
            context.startActivity(intent)
        }
    }
}
