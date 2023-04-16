package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import woowacourse.movie.domain.TicketBundle

class BookCompleteActivity : BackButtonActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_complete)

        val movieBookingData = getMovieBookingInfo()
        initView(movieBookingData)
    }

    private fun getMovieBookingInfo(): MovieBookingInfo {
        val movieBookingData =
            intent.getSerializableCompat(MovieDetailActivity.MOVIE_BOOKING_INFO_KEY)
                ?: MovieBookingInfo.nullData

        if (movieBookingData == MovieBookingInfo.nullData) {
            Toast.makeText(
                this,
                getString(R.string.cant_get_movie_booking_data),
                Toast.LENGTH_SHORT
            ).show()
            this.finish()
        }
        return movieBookingData
    }

    private fun initView(movieBookingData: MovieBookingInfo) {
        findViewById<TextView>(R.id.tv_book_movie_title).text = movieBookingData.movieInfo.title
        findViewById<TextView>(R.id.tv_book_date).text =
            formatBookingTime(movieBookingData.date, movieBookingData.time)
        findViewById<TextView>(R.id.tv_book_person_count).text =
            getString(R.string.book_person_count).format(movieBookingData.ticketCount)
        findViewById<TextView>(R.id.tv_book_total_pay).text =
            getString(R.string.book_total_pay).format(
                TicketBundle(movieBookingData.ticketCount).calculateTotalPrice(
                    movieBookingData.date,
                    movieBookingData.time
                )
            )
    }

    private fun formatBookingTime(date: String, time: String): String {
        val formattedDate: String = date.split("-").joinToString(".")
        return "$formattedDate $time"
    }
}
