package woowacourse.movie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.service.ReservationService

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)

        val reservationId = intent.getLongExtra(RESERVATION_ID, 0)
        initViewData(reservationId)
    }

    private fun initViewData(reservationId: Long) {
        val reservationResult = ReservationService.findReservationResultById(reservationId)

        val titleView = findViewById<TextView>(R.id.movie_title)
        titleView.text = reservationResult.movieTitle
        val screeningDateView = findViewById<TextView>(R.id.movie_screening_date)
        screeningDateView.text =
            reservationResult.screeningDateTime.format(DATE_TIME_FORMATTER)
        val peopleCountView = findViewById<TextView>(R.id.people_count)
        peopleCountView.text = getString(R.string.reservation_people_count_format)
            .format(
                getString(R.string.general_person),
                reservationResult.seatPoints.size,
                reservationResult.seatPoints.map { it.getSeatName() }.sorted().joinToString { it })
        val totalPriceView = findViewById<TextView>(R.id.total_price)
        totalPriceView.text =
            getString(R.string.total_price_format).format(DECIMAL_FORMAT.format(reservationResult.fee))
    }

    private fun Pair<Int, Int>.getSeatName(): String = ('A' + (first - 1)).toString() + second

    companion object {
        const val RESERVATION_ID = "RESERVATION_ID"
    }
}
