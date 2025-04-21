package woowacourse.movie.ui.booking

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.model.booking.BookingResult
import woowacourse.movie.util.Keys

class BookingCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        applySystemBarInsets()

        val bookingResult = bookingResultOrNull() ?: return
        setUpBookingResult(bookingResult)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun applySystemBarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun bookingResultOrNull() =
        IntentCompat.getParcelableExtra(
            intent,
            Keys.Extra.BOOKING_RESULT,
            BookingResult::class.java,
        )

    private fun setUpBookingResult(bookingResult: BookingResult) {
        val cancellationMessage = findViewById<TextView>(R.id.tv_cancellation_guide)
        val completeTitle = findViewById<TextView>(R.id.tv_complete_title)
        val completeScreenDate = findViewById<TextView>(R.id.tv_complete_screening_date)
        val completeScreenTime = findViewById<TextView>(R.id.tv_complete_screening_time)
        val completeHeadCount = findViewById<TextView>(R.id.tv_head_count)
        val completeBookingAmount = findViewById<TextView>(R.id.tv_booking_amount)

        cancellationMessage.text =
            getString(R.string.screening_info, BookingResult.CANCELLATION_LIMIT_MINUTES)
        completeTitle.text = bookingResult.title
        completeScreenDate.text = bookingResult.selectedDate.replace(oldValue = "-", newValue = ".")
        completeScreenTime.text = bookingResult.selectedTime
        completeHeadCount.text =
            getString(R.string.screening_complete_headCount, bookingResult.headCount)
        val bookingAmount: String = DecimalFormat("#,###").format(bookingResult.calculateAmount())
        completeBookingAmount.text =
            getString(R.string.screening_complete_booking_amount, bookingAmount)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
