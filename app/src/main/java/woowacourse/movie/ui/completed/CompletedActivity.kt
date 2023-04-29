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
import woowacourse.movie.ui.seatreservation.uimodel.Seat

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
        val movie = MovieData.findMovieById(reservationInfo.ticket.movieId)
        findViewById<TextView>(R.id.textCompletedTitle).text = movie.title
        findViewById<TextView>(R.id.textCompletedScreeningDate).text =
            reservationInfo.ticket.bookedDateTime.formatScreenDateTime()
        findViewById<TextView>(R.id.textCompletedTicketCount).text =
            getString(R.string.normal_ticket_count).format(reservationInfo.ticket.count)
        findViewById<TextView>(R.id.textCompletedPaymentAmount).text =
            getString(R.string.payment_amount).format(reservationInfo.total)
        findViewById<TextView>(R.id.textCompletedSeat).text =
            reservationInfo.seat.toFormattedString()
    }

    private fun List<Seat>.toFormattedString(): String =
        this.joinToString(prefix = "", separator = ",", postfix = "")

    companion object {
        private const val RESERVATION_INFO = "RESERVATION_INFO"

        fun getIntent(context: Context, reservationInfo: ReservationInfo): Intent {
            return Intent(context, CompletedActivity::class.java).apply {
                putExtra(RESERVATION_INFO, reservationInfo)
            }
        }
    }
}
