package woowacourse.movie.presentation.view

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.uimodel.MovieTicketUiModel

class ReservationResultActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_reservation_result

    override fun onCreateSetup(savedInstanceState: Bundle?) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setUpFromIntent()
    }

    private fun setUpFromIntent() {
        val ticket = intent.getParcelableExtra<MovieTicketUiModel>(INTENT_TICKET)
        val seatsPositions = ticket?.selectedSeats?.joinToString(", ")

        findViewById<TextView>(R.id.title).text = ticket?.title
        findViewById<TextView>(R.id.screeningDate).text = ticket?.screeningDate
        findViewById<TextView>(R.id.screeningTime).text =
            this.getString(R.string.screening_time_format, ticket?.startTime, ticket?.endTime)
        findViewById<TextView>(R.id.runningTime).text =
            this.getString(R.string.running_time, ticket?.runningTime)
        findViewById<TextView>(R.id.reservationInfo).text =
            this.getString(
                R.string.reservation_info_format,
                ticket?.reservationCount,
                seatsPositions,
            )
        findViewById<TextView>(R.id.totalPrice).text =
            this.getString(R.string.reservation_total_price_format, ticket?.totalPrice)
    }

    companion object {
        const val INTENT_TICKET = "ticket"
    }
}
