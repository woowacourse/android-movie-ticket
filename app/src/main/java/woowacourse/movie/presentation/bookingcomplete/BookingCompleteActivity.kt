package woowacourse.movie.presentation.bookingcomplete

import android.content.Context
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.presentation.mapper.toDomain
import woowacourse.movie.presentation.mapper.toUi
import woowacourse.movie.presentation.model.BookingInfoUiModel

class BookingCompleteActivity : AppCompatActivity() {
    private val bookingInfoUiModel: BookingInfoUiModel by lazy { getMovieInfoIntent() }
    private val bookingInfo: BookingInfo by lazy { bookingInfoUiModel.toDomain() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()

        val ticketTotalPrice = DecimalFormat("#,###").format(bookingInfo.totalPrice)

        findViewById<TextView>(R.id.tv_booking_complete_movie_title).text = bookingInfo.movie.title
        findViewById<TextView>(R.id.tv_booking_complete_movie_date_time).text =
            getString(R.string.booking_complete_movie_date_time, bookingInfo.date, bookingInfo.movieTime.toUi())
        findViewById<TextView>(R.id.tv_booking_complete_ticket_count).text =
            getString(R.string.booking_complete_ticket_count, bookingInfo.ticketCount)
        findViewById<TextView>(R.id.tv_booking_complete_ticket_total_price).text =
            getString(R.string.booking_complete_ticket_total_price, ticketTotalPrice)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    private fun getMovieInfoIntent(): BookingInfoUiModel =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(BOOKING_INFO_KEY, BookingInfoUiModel::class.java) ?: BookingInfoUiModel()
        } else {
            intent.getParcelableExtra<BookingInfoUiModel>(BOOKING_INFO_KEY) ?: BookingInfoUiModel()
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
