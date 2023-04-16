package woowacourse.movie

import android.os.Bundle
import android.widget.Toast
import woowacourse.movie.databinding.ActivityBookCompleteBinding
import woowacourse.movie.domain.TicketBundle

class BookCompleteActivity : BackButtonActivity() {
    private lateinit var binding: ActivityBookCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieBookingData =
            intent.getParcelableCompat<MovieBookingInfo>(MOVIE_BOOKING_INFO_INTENT_KEY)
        processEmptyBookingData(movieBookingData)

        setViewData(movieBookingData!!)
    }

    private fun processEmptyBookingData(movieBookingData: MovieBookingInfo?) {
        if (movieBookingData == null) {
            Toast.makeText(this, getString(R.string.error_intent_message), Toast.LENGTH_SHORT)
                .show()
            this.finish()
        }
    }

    private fun setViewData(movieBookingData: MovieBookingInfo) {
        binding.tvBookMovieTitle.text = movieBookingData.movieInfo.title
        binding.tvBookDate.text =
            formatBookingTime(movieBookingData.date, movieBookingData.time)
        binding.tvBookPersonCount.text =
            getString(R.string.book_person_count).format(movieBookingData.ticketCount)
        binding.tvBookTotalPay.text =
            getString(R.string.book_total_pay).format(
                TicketBundle(movieBookingData.ticketCount).calculateTotalPrice(
                    movieBookingData.date, movieBookingData.time
                )
            )
    }

    private fun formatBookingTime(date: String, time: String): String {
        val formattedDate: String = date.split("-").joinToString(".")
        return "$formattedDate $time"
    }

    companion object {
        const val MOVIE_BOOKING_INFO_INTENT_KEY = "MOVIE_BOOKING_INFO_KEY"
    }
}
