package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.presentation.reservation.booking.model.ScreeningMovieUiModel
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs

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
