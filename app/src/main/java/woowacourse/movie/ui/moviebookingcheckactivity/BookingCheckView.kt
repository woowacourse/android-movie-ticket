package woowacourse.movie.ui.moviebookingcheckactivity

import android.view.ViewGroup
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.grade.Position
import woowacourse.movie.domain.price.TicketPrice
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.model.MovieUIModel

class BookingCheckView(
    private val view: ViewGroup,
    movieData: MovieUIModel,
    bookedScreeningDateTime: ScreeningDateTime,
    seatPositions: List<Position>,
    ticketTotalPrice: TicketPrice
) {

    init {
        initMovieInformation(
            movieData,
            bookedScreeningDateTime,
            seatPositions,
            ticketTotalPrice
        )
    }

    private fun initMovieInformation(
        movieData: MovieUIModel,
        bookedScreeningDateTime: ScreeningDateTime,
        seatPositions: List<Position>,
        ticketTotalPrice: TicketPrice
    ) {
        val tvBookingCheckMovieName = view.findViewById<TextView>(R.id.tv_booking_check_movie_name)
        val tvBookingCheckScreeningDay =
            view.findViewById<TextView>(R.id.tv_booking_check_screening_day)
        val tvBookingCheckPersonCount =
            view.findViewById<TextView>(R.id.tv_booking_check_person_count)
        val tvBookingCheckTotalMoney =
            view.findViewById<TextView>(R.id.tv_booking_check_total_money)

        tvBookingCheckMovieName.text = movieData.title
        tvBookingCheckScreeningDay.text =
            bookedScreeningDateTime.time.format(DateTimeFormatters.dateDotTimeColonFormatter)
        tvBookingCheckPersonCount.text =
            view.context.getString(R.string.tv_booking_check_person_count)
                .format(
                    seatPositions.size,
                    seatPositions.joinToString(separator = ",") { convertPositionToPrintFormat(it) }
                )
        tvBookingCheckTotalMoney.text =
            view.context.getString(R.string.tv_booking_check_total_money)
                .format(ticketTotalPrice.value)
    }

    private fun convertPositionToPrintFormat(position: Position): String =
        String.format("%s%d", convertIndexToAlphabet(position.rowIndex), position.columnIndex + 1)

    private fun convertIndexToAlphabet(index: Int): Char {
        val baseNumber = 65
        return (index + baseNumber).toChar()
    }
}
