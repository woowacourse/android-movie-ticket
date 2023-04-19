package woowacourse.movie.ui.ticketingresult

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.extensions.exitForUnNormalCase
import woowacourse.movie.extensions.getParcelableCompat
import woowacourse.movie.model.ReservationUI
import woowacourse.movie.model.TicketUI
import woowacourse.movie.model.mapper.toReservation
import woowacourse.movie.ui.movielist.MovieListActivity
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
        intent.getParcelableCompat<ReservationUI>(TicketingActivity.RESERVATION_KEY).run {
            if (this == null)
                exitForUnNormalCase(MESSAGE_EMPTY_RESERVATION)
            else {
                setReservationInfo(this)
            }
        }
    }

    private fun setReservationInfo(reservationUI: ReservationUI) {
        with(reservationUI) {
            findViewById<TextView>(R.id.tv_title).text = movie.title
            setDateTime(dateTime)
            setTicketCount(ticket)
            setPayment(this)
        }
    }

    private fun setDateTime(dateTime: LocalDateTime) {
        findViewById<TextView>(R.id.tv_date).text = getString(
            R.string.book_date_time,
            dateTime.year,
            dateTime.monthValue,
            dateTime.dayOfMonth,
            dateTime.hour,
            dateTime.minute
        )
    }

    private fun setTicketCount(ticket: TicketUI) {
        findViewById<TextView>(R.id.tv_regular_count).text =
            getString(R.string.regular_count, ticket.count)
    }

    private fun setPayment(reservationUI: ReservationUI) {
        findViewById<TextView>(R.id.tv_pay_result).text =
            getString(
                R.string.movie_pay_result,
                reservationUI.toReservation().totalPrice,
                getString(R.string.on_site_payment)
            )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this@TicketingResultActivity, MovieListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private companion object {
        private const val MESSAGE_EMPTY_RESERVATION = "예약 정보가 없습니다"
    }
}
