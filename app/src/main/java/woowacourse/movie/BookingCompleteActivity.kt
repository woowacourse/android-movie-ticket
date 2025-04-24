package woowacourse.movie

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.mapper.IntentCompat
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.booking.complete.BookingCompleteContract
import woowacourse.movie.model.BookingResult
import woowacourse.movie.booking.complete.BookingCompletePresenter

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

    private fun requireResultOrFinish(): BookingResult? {
        return IntentCompat.getParcelableExtra(
            intent,
            KEY_BOOKING_RESULT,
            BookingResult::class.java,
        )
            ?: run {
                showToastErrorAndFinish(getString(R.string.booking_toast_message))
                throw IllegalStateException("인텐트에 영화 예매 정보가 포함되지 않았습니다")
            }
    }

    override fun showBookingCompleteResult(result: BookingResult) {
        val bookingResultUiData = result.toUiModel(resources)

        val completeTitle = findViewById<TextView>(R.id.tv_complete_title)
        val completeScreenDate = findViewById<TextView>(R.id.tv_complete_screening_date)
        val completeScreenTime = findViewById<TextView>(R.id.tv_complete_screening_time)
        val completeHeadCount = findViewById<TextView>(R.id.tv_head_count)
        val completeBookingAmount = findViewById<TextView>(R.id.tv_booking_amount)

        completeTitle.text = bookingResultUiData.title
        completeScreenDate.text = bookingResultUiData.selectedDateText
        completeScreenTime.text = bookingResultUiData.selectedTimeText
        completeHeadCount.text = bookingResultUiData.headCount
        completeBookingAmount.text = bookingResultUiData.bookingAmountText
    }

    override fun showToastErrorAndFinish(message: String) {
        Log.d("BookingCompleteActivity", message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val KEY_BOOKING_RESULT = "bookingResult"
    }
}
