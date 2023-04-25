package woowacourse.movie.presentation.view.bookcomplete

import android.os.Bundle
import android.widget.Toast
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityBookCompleteBinding
import woowacourse.movie.model.BookingCompleteInfo
import woowacourse.movie.presentation.extension.getParcelableCompat
import woowacourse.movie.presentation.view.common.BackButtonActivity

class BookCompleteActivity : BackButtonActivity() {
    private lateinit var binding: ActivityBookCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookingCompleteInfo =
            intent.getParcelableCompat<BookingCompleteInfo>(BOOKING_COMPLETE_INFO_INTENT_KEY)
        processEmptyBookingData(bookingCompleteInfo)

        setViewData(bookingCompleteInfo!!)
    }

    private fun processEmptyBookingData(bookingCompleteInfo: BookingCompleteInfo?) {
        if (bookingCompleteInfo == null) {
            Toast.makeText(this, getString(R.string.error_intent_message), Toast.LENGTH_SHORT)
                .show()
            this.finish()
        }
    }

    private fun setViewData(bookingCompleteInfo: BookingCompleteInfo) {
        val movieBookingData = bookingCompleteInfo.movieBookingInfo
        binding.tvBookMovieTitle.text = movieBookingData.movieInfo.title
        binding.tvBookDate.text =
            formatBookingTime(movieBookingData.date, movieBookingData.time)
        binding.tvBookPersonCount.text =
            getString(R.string.book_person_info).format(
                movieBookingData.ticketCount,
                bookingCompleteInfo.seats
            )
        binding.tvBookTotalPay.text =
            getString(R.string.book_total_pay).format(
                bookingCompleteInfo.totalPrice
            )
    }

    private fun formatBookingTime(date: String, time: String): String {
        val formattedDate: String = date.split("-").joinToString(".")
        return "$formattedDate $time"
    }

    companion object {
        const val BOOKING_COMPLETE_INFO_INTENT_KEY = "BOOKING_COMPLETE_INFO_INTENT_KEY"
    }
}
