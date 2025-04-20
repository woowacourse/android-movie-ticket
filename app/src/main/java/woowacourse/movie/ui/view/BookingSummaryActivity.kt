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
        val title = findViewById<TextView>(R.id.title)
        val screeningDateTime = findViewById<TextView>(R.id.screeningDateTime)
        val headCount = findViewById<TextView>(R.id.headCount)
        val amount = findViewById<TextView>(R.id.amount)

        title.text = movieTicket.title
        screeningDateTime.text = formatDateTime(movieTicket.screeningDateTime)
        headCount.text = formatHeadCount(getString(R.string.headCount_message), movieTicket.headCount)
        amount.text = formatAmount(getString(R.string.amount_message), movieTicket.amount)
    }
}