package woowacourse.movie.ui.movieselectseatactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.price.TicketCount
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.moviebookingcheckactivity.MovieBookingCheckActivity
import woowacourse.movie.util.getSerializableExtraCompat
import woowacourse.movie.util.intentDataNullProcess

class MovieSelectSeatActivity : AppCompatActivity() {

    lateinit var movieData: MovieUIModel
    lateinit var ticketCount: TicketCount
    lateinit var bookedScreeningDateTime: ScreeningDateTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_select_seat)

        initExtraData()
        SeatView(findViewById(R.id.table_seat))
        SeatConfirmView(findViewById(R.id.layout_total_information), movieData)
    }

    private fun initExtraData() {
        movieData =
            intent.getSerializableExtraCompat(MovieBookingCheckActivity.MOVIE_DATA)
                ?: return this.intentDataNullProcess(
                    MovieBookingCheckActivity.MOVIE_DATA
                )
        ticketCount =
            TicketCount(intent.getIntExtra(MovieBookingCheckActivity.TICKET_COUNT, -1))
        bookedScreeningDateTime =
            ScreeningDateTime(
                intent.getSerializableExtraCompat(MovieBookingCheckActivity.BOOKED_SCREENING_DATE_TIME)
                    ?: return this.intentDataNullProcess(MovieBookingCheckActivity.BOOKED_SCREENING_DATE_TIME),
                ScreeningPeriod(
                    movieData.screeningStartDay,
                    movieData.screeningEndDay
                )
            )
    }

    companion object {
        const val MOVIE_DATA = "movieData"
        const val TICKET_COUNT = "ticketCount"
        const val BOOKED_SCREENING_DATE_TIME = "bookedScreeningDateTime"
    }
}
