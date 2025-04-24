package woowacourse.movie.feature.bookingcomplete.view

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
import woowacourse.movie.R
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.util.getExtra

class BookingCompleteActivity : AppCompatActivity() {
    private val bookingInfoUiModel: BookingInfoUiModel by lazy { intent.getExtra(BOOKING_INFO_KEY) ?: BookingInfoUiModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()

        val ticketTotalPrice = DecimalFormat("#,###").format(bookingInfoUiModel.totalPrice)

        findViewById<TextView>(R.id.tv_booking_complete_movie_title).text = bookingInfoUiModel.movie.title
        findViewById<TextView>(R.id.tv_booking_complete_movie_date_time).text =
            getString(
                R.string.booking_complete_movie_date_time,
                bookingInfoUiModel.date.toString(),
                bookingInfoUiModel.movieTime.toString(),
            )
        findViewById<TextView>(R.id.tv_booking_complete_ticket_count).text =
            getString(R.string.booking_complete_ticket_count, bookingInfoUiModel.ticketCount)
        findViewById<TextView>(R.id.tv_booking_complete_ticket_total_price).text =
            getString(R.string.booking_complete_ticket_total_price, ticketTotalPrice)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
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

    companion object {
        const val BOOKING_INFO_KEY = "booking_info"

        fun newIntent(
            context: Context,
            bookingInfo: BookingInfoUiModel,
        ): Intent =
            Intent(context, BookingCompleteActivity::class.java).apply {
                putExtra(BOOKING_INFO_KEY, bookingInfo)
            }
    }
}
