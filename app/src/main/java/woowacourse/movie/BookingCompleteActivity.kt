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

class BookingCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_booking_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra(MOVIE_TITLE_KEY)
        val date = intent.getStringExtra(MOVIE_DATE_KEY)
        val time = intent.getStringExtra(MOVIE_TIME_KEY)
        val ticketCount = intent.getIntExtra(TICKET_COUNT_KEY, 0)
        val ticketTotalPrice = DecimalFormat("#,###").format(ticketCount * 13000)

        findViewById<TextView>(R.id.tv_booking_complete_movie_title).text = title
        findViewById<TextView>(R.id.tv_booking_complete_movie_date_time).text =
            getString(R.string.booking_complete_movie_date_time, date, time)
        findViewById<TextView>(R.id.tv_booking_complete_ticket_count).text = getString(R.string.booking_complete_ticket_count, ticketCount)
        findViewById<TextView>(R.id.tv_booking_complete_ticket_total_price).text =
            getString(R.string.booking_complete_ticket_total_price, ticketTotalPrice)
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

        fun newIntent(
            context: Context,
            title: String,
            date: String,
            time: String,
            ticketCount: Int,
        ): Intent =
            Intent(context, BookingCompleteActivity::class.java).apply {
                putExtra(MOVIE_TITLE_KEY, title)
                putExtra(MOVIE_DATE_KEY, date)
                putExtra(MOVIE_TIME_KEY, time)
                putExtra(TICKET_COUNT_KEY, ticketCount)
            }
    }
}
