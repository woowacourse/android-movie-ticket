package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.util.parcelableExtraWithVersion

class BookingCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()

        val ticketInfo = intent.parcelableExtraWithVersion(TICKET_INFO_KEY, TicketInfo::class.java)

        val title = ticketInfo?.movie?.title ?: ""
        val date = ticketInfo?.date ?: ""
        val time = ticketInfo?.time ?: ""
        val ticketCount = ticketInfo?.count ?: 0
        val ticketTotalPrice = DecimalFormat("#,###").format(ticketCount * 13000)

        findViewById<TextView>(R.id.tv_booking_complete_movie_title).text = title
        findViewById<TextView>(R.id.tv_booking_complete_movie_date_time).text =
            getString(R.string.booking_complete_movie_date_time, date, time)
        findViewById<TextView>(R.id.tv_booking_complete_ticket_count).text =
            getString(R.string.booking_complete_ticket_count, ticketCount)
        findViewById<TextView>(R.id.tv_booking_complete_ticket_total_price).text =
            getString(R.string.booking_complete_ticket_total_price, ticketTotalPrice)
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_booking_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val MOVIE_TITLE_KEY = "movie_title"
        const val MOVIE_DATE_KEY = "movie_date"
        const val MOVIE_TIME_KEY = "movie_time"
        const val TICKET_COUNT_KEY = "ticket_count"
        const val TICKET_INFO_KEY = "ticket_info"

        fun newIntent(
            context: Context,
            ticketInfo: TicketInfo,
        ): Intent =
            Intent(context, BookingCompleteActivity::class.java).apply {
                putExtra(TICKET_INFO_KEY, ticketInfo)
            }
    }
}
