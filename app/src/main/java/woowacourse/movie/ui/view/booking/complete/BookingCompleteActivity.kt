package woowacourse.movie.ui.view.booking.complete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.presenter.booking.complete.BookingCompleteContract
import woowacourse.movie.presenter.booking.complete.BookingCompletePresenter
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.seat.SeatsUiModel
import woowacourse.movie.util.DialogUtil

class BookingCompleteActivity : AppCompatActivity(), BookingCompleteContract.View {
    private val presenter: BookingCompletePresenter by lazy { generatePresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        applySystemBarInsets()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadBookingResult(bookingResultUiOrNull())
        presenter.loadSeats(seatsUiOrNull())
    }

    override fun showBookingResult(bookingResultUiModel: BookingResultUiModel) {
        val cancellationMessage = findViewById<TextView>(R.id.tv_cancellation_guide)
        val completeTitle = findViewById<TextView>(R.id.tv_complete_title)
        val completeScreenDate = findViewById<TextView>(R.id.tv_complete_screening_date)
        val completeScreenTime = findViewById<TextView>(R.id.tv_complete_screening_time)
        val completeHeadCount = findViewById<TextView>(R.id.tv_head_count)

        cancellationMessage.text =
            getString(R.string.screening_info, BookingResult.CANCELLATION_LIMIT_MINUTES)
        completeTitle.text = bookingResultUiModel.title
        completeScreenDate.text = bookingResultUiModel.selectedDate
        completeScreenTime.text = bookingResultUiModel.selectedTime
        completeHeadCount.text =
            getString(R.string.screening_complete_headCount, bookingResultUiModel.headCount)
    }

    override fun showSeatsPosition(seatsPosition: String) {
        val seatsPositionView = findViewById<TextView>(R.id.tv_seats_position)
        seatsPositionView.text = getString(R.string.screening_complete_seats_position, seatsPosition)
    }

    override fun showTotalPrice(totalPrice: String) {
        val bookingAmountView = findViewById<TextView>(R.id.tv_booking_amount)
        bookingAmountView.text = getString(R.string.screening_complete_booking_amount, totalPrice)
    }

    override fun showErrorMessage(messageResource: Int) {
        DialogUtil.showError(
            activity = this@BookingCompleteActivity,
            message = getString(messageResource),
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun generatePresenter(): BookingCompletePresenter {
        return BookingCompletePresenter(this@BookingCompleteActivity)
    }

    private fun applySystemBarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun bookingResultUiOrNull() =
        IntentCompat.getParcelableExtra(
            intent,
            EXTRA_BOOKING_RESULT,
            BookingResultUiModel::class.java,
        )

    private fun seatsUiOrNull() =
        IntentCompat.getParcelableExtra(
            intent,
            EXTRA_BOOKING_SEATS,
            SeatsUiModel::class.java,
        )

    companion object {
        private const val EXTRA_BOOKING_RESULT = "extra_booking_result"
        private const val EXTRA_BOOKING_SEATS = "extra_booking_seats"

        fun newIntent(
            context: Context,
            bookingResultUiModel: BookingResultUiModel,
            seats: SeatsUiModel,
        ): Intent {
            return Intent(
                context,
                BookingCompleteActivity::class.java,
            ).apply {
                putExtra(EXTRA_BOOKING_RESULT, bookingResultUiModel)
                putExtra(EXTRA_BOOKING_SEATS, seats)
            }
        }
    }
}
