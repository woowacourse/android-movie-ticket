package woowacourse.movie

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.booking.complete.BookingCompleteContract
import woowacourse.movie.booking.complete.BookingCompletePresenter
import woowacourse.movie.booking.detail.TicketUiModel
import woowacourse.movie.mapper.IntentCompat

class BookingCompleteActivity : AppCompatActivity(), BookingCompleteContract.View {
    private lateinit var presenter: BookingCompleteContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        setUpUi()

        val bookingResult = requireResultOrFinish()
        presenter = BookingCompletePresenter(this, bookingResult)
        presenter.initializeData(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpUi() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun requireResultOrFinish(): TicketUiModel? {
        return IntentCompat.getParcelableExtra(
            intent,
            KEY_BOOKING_RESULT,
            TicketUiModel::class.java,
        )
            ?: run {
                Log.e(TAG, "인텐트에 영화 예매 정보(KEY_BOOKING_RESULT)가 없습니다.")
                showToastErrorAndFinish(getString(R.string.booking_toast_message))
                null
            }
    }

    override fun showBookingCompleteResult(ticketUiData: TicketUiModel) {
        val completeTitle = findViewById<TextView>(R.id.tv_complete_title)
        val completeScreenDate = findViewById<TextView>(R.id.tv_complete_screening_date)
        val completeScreenTime = findViewById<TextView>(R.id.tv_complete_screening_time)
        val completeHeadCount = findViewById<TextView>(R.id.tv_head_count)
        val completeTotalAmount = findViewById<TextView>(R.id.tv_booking_amount)
        val completeSeats = findViewById<TextView>(R.id.tv_seats)

        val completeHeadCountText =
            getString(R.string.screening_complete_headCount, ticketUiData.headCount)
        val totalPriceText =
            getString(R.string.screening_complete_booking_amount, ticketUiData.totalPrice)

        completeTitle.text = ticketUiData.title
        completeScreenDate.text = ticketUiData.selectedDateText
        completeScreenTime.text = ticketUiData.selectedTimeText
        completeHeadCount.text = completeHeadCountText
        completeTotalAmount.text = totalPriceText
        completeSeats.text = ticketUiData.seats
    }

    override fun showToastErrorAndFinish(message: String) {
        Log.d(TAG, message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        private const val TAG = "BookingCompleteActivity"
        const val KEY_BOOKING_RESULT = "bookingResult"
    }
}
