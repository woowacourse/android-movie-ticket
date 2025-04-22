package woowacourse.movie.ui.view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.ui.constant.IntentKeys
import woowacourse.movie.ui.util.TicketUiFormatter.formatAmount
import woowacourse.movie.ui.util.TicketUiFormatter.formatDateTime
import woowacourse.movie.ui.util.TicketUiFormatter.formatHeadCount
import woowacourse.movie.ui.util.intentSerializable

class BookingSummaryActivity : BaseActivity() {
    override val layoutRes: Int
        get() = R.layout.activity_bookingsummary

    private lateinit var ticket: MovieTicket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!fetchTicketFromIntent()) return
        setupScreen(layoutRes)
        displayBookingSummary()
    }

    private fun fetchTicketFromIntent(): Boolean {
        val data = intent.intentSerializable(IntentKeys.TICKET, MovieTicket::class.java)
        if (data == null) {
            Toast.makeText(this, TICKET_INTENT_ERROR, Toast.LENGTH_SHORT).show()
            finish()
            return false
        }
        ticket = data
        return true
    }

    private fun displayBookingSummary() {
        val notice = findViewById<TextView>(R.id.textview_notice)
        val title = findViewById<TextView>(R.id.textview_title)
        val screeningDateTime = findViewById<TextView>(R.id.textview_screeningdatetime)
        val headCount = findViewById<TextView>(R.id.textview_headcount)
        val amount = findViewById<TextView>(R.id.textview_amount)

        notice.text = String.format(getString(R.string.cancel_notice), CANCELABLE_TIME)
        title.text = ticket.title
        screeningDateTime.text = formatDateTime(ticket.screeningDateTime)
        headCount.text = formatHeadCount(getString(R.string.headCount_message), ticket.headCount)
        amount.text = formatAmount(getString(R.string.amount_message), ticket.amount)
    }

    companion object {
        private const val CANCELABLE_TIME = 15
        private const val TICKET_INTENT_ERROR = "[ERROR] 예매 정보에 대한 키 값이 올바르지 않습니다."
    }
}