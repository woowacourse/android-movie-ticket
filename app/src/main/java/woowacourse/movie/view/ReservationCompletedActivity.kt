package woowacourse.movie.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Reservation
import java.text.DecimalFormat

class ReservationCompletedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_completed)

        val reservation = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(ReservationActivity.RESERVATION, Reservation::class.java)
        } else {
            intent.getSerializableExtra(ReservationActivity.RESERVATION) as? Reservation
        }
        requireNotNull(reservation) { "인텐트로 받아온 데이터가 널일 수 없습니다." }
        initViewData(reservation)
    }

    private fun initViewData(reservation: Reservation) {
        val titleView = findViewById<TextView>(R.id.movie_title)
        titleView.text = reservation.movieTitle
        val screeningDateView = findViewById<TextView>(R.id.movie_screening_date)
        screeningDateView.text = reservation.movieScreeningDate.toString()
        val peopleCountView = findViewById<TextView>(R.id.people_count)
        peopleCountView.text = getString(R.string.reservation_people_count_format)
            .format(getString(R.string.general_person), reservation.peopleCount)
        val totalPriceView = findViewById<TextView>(R.id.total_price)
        totalPriceView.text =
            getString(R.string.total_price_format).format(DECIMAL_FORMAT.format(reservation.totalReservationFee.amount))
    }

    companion object {
        private val DECIMAL_FORMAT = DecimalFormat("#,###")
    }
}
