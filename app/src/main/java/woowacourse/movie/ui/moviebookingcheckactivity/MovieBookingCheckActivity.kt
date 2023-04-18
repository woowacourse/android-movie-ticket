package woowacourse.movie.ui.moviebookingcheckactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.price.TicketCount
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.util.customGetSerializableExtra

class MovieBookingCheckActivity : AppCompatActivity() {

    lateinit var movieData: MovieUIModel
    lateinit var ticketCount: TicketCount
    lateinit var bookedScreeningDateTime: ScreeningDateTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking_check)

        initExtraData()
        initBookingCheckView()
    }

    private fun initExtraData() {
        movieData = intent.customGetSerializableExtra(MOVIE_DATA) ?: throw IllegalStateException(
            INTENT_EXTRA_INITIAL_ERROR
        )
        ticketCount =
            intent.customGetSerializableExtra(TICKET_COUNT) ?: throw IllegalStateException(
                INTENT_EXTRA_INITIAL_ERROR
            )
        bookedScreeningDateTime =
            intent.customGetSerializableExtra(BOOKED_SCREENING_DATE_TIME)
                ?: throw IllegalStateException(INTENT_EXTRA_INITIAL_ERROR)
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

        private const val INTENT_EXTRA_INITIAL_ERROR = "intent 의 데이터 이동시 data가 null으로 넘어오고 있습니다"
    }
}
