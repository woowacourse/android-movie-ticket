package woowacourse.movie.ui.view

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.ui.view.TicketUiFormatter.formatAmount
import woowacourse.movie.ui.view.TicketUiFormatter.formatDateTime
import woowacourse.movie.ui.view.TicketUiFormatter.formatHeadCount

class BookingSummaryActivity : BaseActivity() {
    override val layoutRes: Int
        get() = R.layout.activity_bookingsummary
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(layoutRes)
        displayBookingSummary()
    }

    private fun displayBookingSummary() {
        val movieTicket = intent.intentSerializable(IntentKeys.TICKET, MovieTicket::class.java)
            ?: throw IllegalArgumentException(TICKET_INTENT_ERROR)
        val notice = findViewById<TextView>(R.id.textview_notice)
        val title = findViewById<TextView>(R.id.textview_title)
        val screeningDateTime = findViewById<TextView>(R.id.textview_screeningdatetime)
        val headCount = findViewById<TextView>(R.id.textview_headcount)
        val amount = findViewById<TextView>(R.id.textview_amount)

        notice.text = String.format(getString(R.string.cancel_notice), CANCELABLE_TIME)
        title.text = movieTicket.title
        screeningDateTime.text = formatDateTime(movieTicket.screeningDateTime)
        headCount.text = formatHeadCount(getString(R.string.headCount_message), movieTicket.headCount)
        amount.text = formatAmount(getString(R.string.amount_message), movieTicket.amount)
    }

    companion object {
        private const val CANCELABLE_TIME = 15
        private const val TICKET_INTENT_ERROR = "[ERROR] 예매 정보에 대한 키 값이 올바르지 않습니다."
    }
}