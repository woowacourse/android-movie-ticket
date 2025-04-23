package woowacourse.movie.ui.view.booking

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.util.Keys
import woowacourse.movie.util.mapper.BookingResultModelMapper

class BookingCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        applySystemBarInsets()

        val bookingResultUi = bookingResultUiOrNull() ?: return
        setUpBookingResult(bookingResultUi)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            Keys.Extra.BOOKING_RESULT,
            BookingResultUiModel::class.java,
        )

    private fun setUpBookingResult(bookingResultUiModel: BookingResultUiModel) {
        val cancellationMessage = findViewById<TextView>(R.id.tv_cancellation_guide)
        val completeTitle = findViewById<TextView>(R.id.tv_complete_title)
        val completeScreenDate = findViewById<TextView>(R.id.tv_complete_screening_date)
        val completeScreenTime = findViewById<TextView>(R.id.tv_complete_screening_time)
        val completeHeadCount = findViewById<TextView>(R.id.tv_head_count)
        val completeBookingAmount = findViewById<TextView>(R.id.tv_booking_amount)

        cancellationMessage.text =
            getString(R.string.screening_info, BookingResult.CANCELLATION_LIMIT_MINUTES)
        completeTitle.text = bookingResultUiModel.title
        completeScreenDate.text = bookingResultUiModel.selectedDate
        completeScreenTime.text = bookingResultUiModel.selectedTime
        completeHeadCount.text =
            getString(R.string.screening_complete_headCount, bookingResultUiModel.headCount)

        val money = BookingResultModelMapper.toDomain(bookingResultUiModel).calculateAmount()
        val bookingAmount: String = DecimalFormat("#,###").format(money)
        completeBookingAmount.text =
            getString(R.string.screening_complete_booking_amount, bookingAmount)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
