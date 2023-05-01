package woowacourse.movie.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.TicketState
import woowacourse.movie.ui.DateTimeFormatters.dateDotTimeColonFormatter
import woowacourse.movie.util.customGetParcelableExtra

class MovieBookingCheckActivity : AppCompatActivity() {

    private lateinit var ticketState: TicketState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking_check)

        initExtraData()
        initMovieInformation()
    }

    private fun initExtraData() {
        ticketState = intent.customGetParcelableExtra(TICKET)
            ?: return notFoundData(TICKET)
    }

    private fun notFoundData(key: String) {
        Log.d(MOIVE_BOOKING_CHECK_LOG_MSG, DATA_NOT_FOUNT_ERROR_MSG.format(key))
        finish()
    }

    private fun initMovieInformation() {
        val tvBookingCheckMovieName = findViewById<TextView>(R.id.tv_booking_check_movie_name)
        val tvBookingCheckScreeningDay = findViewById<TextView>(R.id.tv_booking_check_screening_day)
        val tvBookedTicketInfo = findViewById<TextView>(R.id.tv_booked_ticket_info)
        val tvBookingCheckTotalMoney = findViewById<TextView>(R.id.tv_booking_check_total_money)

        tvBookingCheckMovieName.text = ticketState.movieData.title
        tvBookingCheckScreeningDay.text =
            ticketState.screeningDateTime.dateTime.format(dateDotTimeColonFormatter)
        tvBookedTicketInfo.text =
            getString(R.string.tv_booking_check_person_count).format(ticketState.count.value, ticketState.seatSelection.map { it.toString() }.sorted().joinToString(", "))
        tvBookingCheckTotalMoney.text =
            getString(R.string.tv_booking_check_total_money).format(ticketState.price)
    }

    companion object {
        private const val TICKET = "ticket"
        private const val DATA_NOT_FOUNT_ERROR_MSG = "%s를 찾을 수 없습니다."
        private const val MOIVE_BOOKING_CHECK_LOG_MSG = "MovieBookingCheck"
    }
}
