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
import woowacourse.movie.feature.bookingcomplete.contract.BookingCompleteContract
import woowacourse.movie.feature.bookingcomplete.presenter.BookingCompletePresenter
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.util.getExtra

class BookingCompleteActivity :
    AppCompatActivity(),
    BookingCompleteContract.View {
    private val presenter: BookingCompleteContract.Presenter by lazy { BookingCompletePresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        presenter.prepareBookingInfo(bookingInfo = intent.getExtra(BOOKING_INFO_KEY) ?: BookingInfoUiModel())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) presenter.quitBookingInfo()
        return super.onOptionsItemSelected(item)
    }

    override fun showBookingResult(bookingInfo: BookingInfoUiModel) {
        val ticketTotalPrice = MONEY_DECIMAL_FORMAT.format(bookingInfo.totalPrice)

        findViewById<TextView>(R.id.tv_booking_complete_movie_title).text = bookingInfo.movie.title
        findViewById<TextView>(R.id.tv_booking_complete_movie_date_time).text =
            getString(
                R.string.booking_complete_movie_date_time,
                bookingInfo.date.toString(),
                bookingInfo.movieTime.toString(),
            )
        findViewById<TextView>(R.id.tv_booking_complete_ticket_count).text =
            getString(R.string.booking_complete_ticket_count, bookingInfo.ticketCount)
        findViewById<TextView>(R.id.tv_booking_complete_ticket_total_price).text =
            getString(R.string.booking_complete_ticket_total_price, ticketTotalPrice)
        findViewById<TextView>(R.id.tv_booking_complete_ticket_seats).text =
            bookingInfo.selectedSeats.joinToString { it.toLabel() }
    }

    override fun navigateToBack() {
        finish()
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
        private const val BOOKING_INFO_KEY = "BOOKING_INFO"
        private val MONEY_DECIMAL_FORMAT = DecimalFormat("#,###")

        fun newIntent(
            context: Context,
            bookingInfo: BookingInfoUiModel,
        ): Intent =
            Intent(context, BookingCompleteActivity::class.java).apply {
                putExtra(BOOKING_INFO_KEY, bookingInfo)
            }
    }
}
