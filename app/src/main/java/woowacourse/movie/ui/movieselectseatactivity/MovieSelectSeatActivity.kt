package woowacourse.movie.ui.movieselectseatactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.grade.SelectedSeats
import woowacourse.movie.domain.price.TicketCount
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.model.PositionUIModel
import woowacourse.movie.ui.model.PositionUIModel.Companion.toPosition
import woowacourse.movie.ui.model.PositionUIModel.Companion.toPositionUIModel
import woowacourse.movie.ui.moviebookingcheckactivity.MovieBookingCheckActivity
import woowacourse.movie.util.getParcelableArrayListCompat
import woowacourse.movie.util.getSerializableExtraCompat
import woowacourse.movie.util.intentDataNullProcess

class MovieSelectSeatActivity : AppCompatActivity() {

    lateinit var movieData: MovieUIModel
    lateinit var ticketCount: TicketCount
    lateinit var bookedScreeningDateTime: ScreeningDateTime
    lateinit var seatConfirmView: SeatConfirmView
    lateinit var seatView: SeatView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_select_seat)

        initExtraData()
        initSeatConfirmView()
        initSeatView(savedInstanceState)
        recoverState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(
            PARCELEBLE_SEATS,
            seatView.selectedSeats.seats.map { it.toPositionUIModel() }.toCollection(ArrayList())
        )
        super.onSaveInstanceState(outState)
    }

    private fun recoverState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            seatView = SeatView(
                findViewById(R.id.table_seat),
                SelectedSeats(
                    seats = savedInstanceState.getParcelableArrayListCompat<PositionUIModel>(
                        PARCELEBLE_SEATS
                    )?.map { it.toPosition() }?.toMutableList() ?: throw IllegalStateException(),
                    ticketCount = ticketCount,
                    bookedScreeningDateTime = bookedScreeningDateTime
                ),
                seatConfirmView::updateMovieTotalPrice,
                seatConfirmView::updateBtnCheckState
            )
        }
    }

    private fun initSeatView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            seatView = SeatView(
                findViewById(R.id.table_seat),
                SelectedSeats(
                    ticketCount = ticketCount,
                    bookedScreeningDateTime = bookedScreeningDateTime
                ),
                seatConfirmView::updateMovieTotalPrice,
                seatConfirmView::updateBtnCheckState
            )
        }
    }

    private fun initSeatConfirmView() {
        seatConfirmView = SeatConfirmView(findViewById(R.id.layout_total_information), movieData)
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

        const val PARCELEBLE_SEATS = "serializable_seats"
    }
}
