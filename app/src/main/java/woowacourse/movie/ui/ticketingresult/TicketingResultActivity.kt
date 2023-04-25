package woowacourse.movie.ui.ticketingresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.extensions.exitForUnNormalCase
import woowacourse.movie.extensions.getParcelableCompat
import woowacourse.movie.model.ReservationUI
import woowacourse.movie.model.TicketsUI
import woowacourse.movie.model.mapper.toTickets
import woowacourse.movie.ui.ticketing.TicketingActivity
import java.time.LocalDateTime

class TicketingResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initReservation()
    }

    private fun initReservation() {
        val reservation =
            intent.getParcelableCompat<ReservationUI>(TicketingActivity.RESERVATION_KEY)
                ?: return exitForUnNormalCase(MESSAGE_EMPTY_RESERVATION)
        setReservationInfo(reservation)
    }

    private fun setReservationInfo(reservation: ReservationUI) {
        with(reservation) {
            findViewById<TextView>(R.id.tv_title).text = movie.title
            setDate(reservation.dateTime)
            setTime(reservation.dateTime)
            setTicketCount(ticketsUI)
            setPayment(this)
        }
    }

    private fun setDate(dateTime: LocalDateTime) {
        findViewById<TextView>(R.id.tv_date).text = getString(
            R.string.book_date,
            dateTime.year,
            dateTime.monthValue,
            dateTime.dayOfMonth,
        )
    }

    private fun setTime(dateTime: LocalDateTime) {
        findViewById<TextView>(R.id.tv_time).text = getString(
            R.string.book_time,
            dateTime.hour,
            dateTime.minute
        )
    }

    private fun setTicketCount(tickets: TicketsUI) {
        findViewById<TextView>(R.id.tv_regular_count).text =
            getString(
                R.string.regular_count,
                tickets.toTickets().size,
                getString(
                    R.string.reservation_seat_position,
                    tickets.getSeatPositionUIFormat()
                )
            )
    }

    private fun setPayment(reservation: ReservationUI) {
        findViewById<TextView>(R.id.tv_pay_result).text =
            getString(
                R.string.movie_pay_result,
                calculateTicketPrice(reservation),
                getString(R.string.on_site_payment)
            )
    }

    private fun calculateTicketPrice(reservation: ReservationUI): Int {
        return reservation.ticketsUI.toTickets().getTotalPrice(reservation.dateTime)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MESSAGE_EMPTY_RESERVATION = "예약 정보가 없습니다"

        internal fun getIntent(context: Context, reservationUI: ReservationUI): Intent {
            val intent = Intent(context, TicketingResultActivity::class.java)
            intent.putExtra(TicketingActivity.RESERVATION_KEY, reservationUI)
            return intent
        }
    }
}
