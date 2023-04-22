package woowacourse.movie.ui.moviebookingcheckactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.grade.Position
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.model.PositionUIModel
import woowacourse.movie.ui.model.PositionUIModel.Companion.toPosition
import woowacourse.movie.util.getParcelableArrayListExtraCompat
import woowacourse.movie.util.getSerializableExtraCompat
import woowacourse.movie.util.intentDataNullProcess
import kotlin.properties.Delegates

class MovieBookingCheckActivity : AppCompatActivity() {

    lateinit var movieData: MovieUIModel
    lateinit var bookedScreeningDateTime: ScreeningDateTime
    lateinit var seatPositions: List<Position>
    var ticketTotalPrice by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking_check)

        initExtraData()
//        initBookingCheckView()
    }

    private fun initExtraData() {
        movieData =
            intent.getSerializableExtraCompat(MOVIE_DATA) ?: return this.intentDataNullProcess(
                MOVIE_DATA
            )
        bookedScreeningDateTime =
            ScreeningDateTime(
                intent.getSerializableExtraCompat(BOOKED_SCREENING_DATE_TIME)
                    ?: return this.intentDataNullProcess(BOOKED_SCREENING_DATE_TIME),
                ScreeningPeriod(
                    movieData.screeningStartDay,
                    movieData.screeningEndDay
                )
            )
        seatPositions = intent.getParcelableArrayListExtraCompat<PositionUIModel>(SEAT_POSITIONS)
            ?.map { it.toPosition() } ?: return this.intentDataNullProcess(SEAT_POSITIONS)
        ticketTotalPrice = intent.getIntExtra(TICKET_TOTAL_PRICE, -1)
    }

//    private fun initBookingCheckView() {
//        BookingCheckView(
//            findViewById(R.id.layout_booking_check),
//            movieData,
//            bookedScreeningDateTime,
//            ticketCount
//        )
//    }

    companion object {
        const val MOVIE_DATA = "movieData"
        const val BOOKED_SCREENING_DATE_TIME = "bookedScreeningDateTime"
        const val SEAT_POSITIONS = "seat_postitions"
        const val TICKET_TOTAL_PRICE = "ticket_total_price"
    }
}
