package woowacourse.movie.ui.completed

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.MovieData
import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.formatScreenDateTime

class CompletedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed)

        initView(getReservationInfo())
    }

    private fun getReservationInfo() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getParcelableExtra(RESERVATION_INFO, ReservationInfo::class.java)
            ?: throw IllegalArgumentException()
    } else {
        intent.getParcelableExtra(RESERVATION_INFO) ?: throw IllegalArgumentException()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView(reservationInfo: ReservationInfo) {
        val ticket = reservationInfo.ticket
        val title = MovieData.findMovieById(ticket.movieId).title
        val ticketCount = getString(R.string.normal_ticket_count).format(ticket.count)
        val payment = getString(R.string.payment_amount).format(reservationInfo.total)
        val screeningDate = ticket.bookedDateTime.formatScreenDateTime()
        val seats = reservationInfo.seat.toFormattedString()

        R.id.textCompletedTitle.setText(title)
        R.id.textCompletedScreeningDate.setText(screeningDate)
        R.id.textCompletedTicketCount.setText(ticketCount)
        R.id.textCompletedPaymentAmount.setText(payment)
        R.id.textCompletedSeat.setText(seats)
    }

    private fun Int.setText(content: String) {
        findViewById<TextView>(this).text = content
    }

    private fun List<String>.toFormattedString(): String =
        this.joinToString(BLANK, SEPARATOR, BLANK)

    companion object {
        private const val BLANK = ""
        private const val SEPARATOR = ","
        private const val RESERVATION_INFO = "RESERVATION_INFO"

        fun getIntent(context: Context, reservationInfo: ReservationInfo): Intent {
            return Intent(context, CompletedActivity::class.java).apply {
                putExtra(RESERVATION_INFO, reservationInfo)
            }
        }
    }
}
