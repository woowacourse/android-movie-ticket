package woowacourse.movie

import android.content.Context
import android.content.Intent
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
                ?: MovieBookingInfo.emptyData

        if (movieBookingData == MovieBookingInfo.emptyData) {
            Toast.makeText(
                this,
                getString(R.string.cant_get_movie_booking_data),
                Toast.LENGTH_LONG
            ).show()
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

    companion object {
        fun intent(context: Context) = Intent(context, BookCompleteActivity::class.java)
    }
}
