package woowacourse.movie.ui.view.booking

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.data.MovieTicket
import woowacourse.movie.ui.view.utils.CustomFormatter.formatAmount
import woowacourse.movie.ui.view.utils.CustomFormatter.formatDateTime
import woowacourse.movie.ui.view.utils.CustomFormatter.formatHeadCount
import woowacourse.movie.ui.view.utils.intentSerializable

class BookingSummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen()
        displayBookingSummary()
    }

    private fun setupScreen() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_bookingsummary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun displayBookingSummary() {
        val movieTicket =
            intent.intentSerializable(getString(R.string.ticket_info_key), MovieTicket::class.java)
        val title = findViewById<TextView>(R.id.title)
        val screeningDateTime = findViewById<TextView>(R.id.screeningDateTime)
        val headCount = findViewById<TextView>(R.id.headCount)
        val amount = findViewById<TextView>(R.id.amount)

        title.text = movieTicket.title
        screeningDateTime.text = formatDateTime(movieTicket.screeningDateTime)
        headCount.text =
            formatHeadCount(getString(R.string.headCount_message), movieTicket.headCount)
        amount.text = formatAmount(getString(R.string.amount_message), movieTicket.amount)
    }
}
