package woowacourse.movie.ui.moviebookingcheckactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.price.TicketCount
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.util.getSerializableExtraCompat
import woowacourse.movie.util.intentDataNullProcess

class MovieBookingCheckActivity : AppCompatActivity() {

    lateinit var movieData: MovieUIModel
    lateinit var ticketCount: woowacourse.movie.domain.price.TicketCount
    lateinit var bookedScreeningDateTime: woowacourse.movie.domain.datetime.ScreeningDateTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking_check)

        initExtraData()
        initBookingCheckView()
    }

    private fun initExtraData() {
        movieData =
            intent.getSerializableExtraCompat(MOVIE_DATA) ?: return this.intentDataNullProcess(
                MOVIE_DATA
            )
        ticketCount =
            woowacourse.movie.domain.price.TicketCount(intent.getIntExtra(TICKET_COUNT, -1))
        bookedScreeningDateTime =
            woowacourse.movie.domain.datetime.ScreeningDateTime(
                intent.getSerializableExtraCompat(BOOKED_SCREENING_DATE_TIME)
                    ?: return this.intentDataNullProcess(BOOKED_SCREENING_DATE_TIME),
                woowacourse.movie.domain.datetime.ScreeningPeriod(
                    movieData.screeningStartDay,
                    movieData.screeningEndDay
                )
            )
    }

    private fun initBookingCheckView() {
        BookingCheckView(
            findViewById(R.id.layout_booking_check),
            movieData,
            bookedScreeningDateTime,
            ticketCount
        )
    }

    companion object {
        const val MOVIE_DATA = "movieData"
        const val TICKET_COUNT = "ticketCount"
        const val BOOKED_SCREENING_DATE_TIME = "bookedScreeningDateTime"
    }
}
