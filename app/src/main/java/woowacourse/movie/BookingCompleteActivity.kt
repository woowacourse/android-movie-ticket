package woowacourse.movie

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.BookingResult

class BookingCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)
        setUpUi()

        val bookingResult = bookingResultOrNull() ?: return
        setUpBookingResult(bookingResult)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpUi() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun bookingResultOrNull() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("bookingResult", BookingResult::class.java)
        } else {
            intent.getParcelableExtra("bookingResult")
        }

    private fun setUpBookingResult(bookingResult: BookingResult) {
        val completeTitle = findViewById<TextView>(R.id.tv_complete_title)
        val completeScreenDate = findViewById<TextView>(R.id.tv_complete_screening_date)
        val completeScreenTime = findViewById<TextView>(R.id.tv_complete_screening_time)
        val completeHeadCount = findViewById<TextView>(R.id.tv_head_count)
        val completeBookingAmount = findViewById<TextView>(R.id.tv_booking_amount)

        completeTitle.text = bookingResult.title
        completeScreenDate.text = formatDate(bookingResult.selectedDate)
        completeScreenTime.text = bookingResult.selectedTime
        completeHeadCount.text =
            getString(R.string.screening_complete_headCount, bookingResult.headCount)
        val bookingAmount: String = DecimalFormat("#,###").format(bookingResult.calculateAmount())
        completeBookingAmount.text =
            getString(R.string.screening_complete_booking_amount, bookingAmount)
    }

    private fun formatDate(date: String): String {
        return date.replace("-", ".")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
