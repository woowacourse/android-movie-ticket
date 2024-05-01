package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs
import woowacourse.movie.presentation.screening.ScreeningMovieUiModel

interface MovieReservationView {
    fun showMovieReservation(reservation: ScreeningMovieUiModel)

    fun showErrorView()

    fun showHeadCount(count: Int)

    fun showTimePicker(times: List<String>)

    fun showScreenDateAt(position: Int)

    fun showTimePickerAt(position: Int)

    fun showDatePicker(dates: List<String>)

    fun navigateToSeatSelection(navArgs: SeatSelectionNavArgs)
}
