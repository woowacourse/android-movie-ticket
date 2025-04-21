package woowacourse.movie.ui.view

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.ui.view.CustomFormatter.formatAmount
import woowacourse.movie.ui.view.CustomFormatter.formatDateTime
import woowacourse.movie.ui.view.CustomFormatter.formatHeadCount

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
        val title = findViewById<TextView>(R.id.textview_title)
        val screeningDateTime = findViewById<TextView>(R.id.textview_screeningdatetime)
        val headCount = findViewById<TextView>(R.id.textview_headcount)
        val amount = findViewById<TextView>(R.id.textview_amount)

        title.text = movieTicket.title
        screeningDateTime.text = formatDateTime(movieTicket.screeningDateTime)
        headCount.text = formatHeadCount(getString(R.string.headCount_message), movieTicket.headCount)
        amount.text = formatAmount(getString(R.string.amount_message), movieTicket.amount)
    }

    companion object {
        private const val TICKET_INTENT_ERROR = "[ERROR] 예매 정보에 대한 키 값이 올바르지 않습니다."
    }
}