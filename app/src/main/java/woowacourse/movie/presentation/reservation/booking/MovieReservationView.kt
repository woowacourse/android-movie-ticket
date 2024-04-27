package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.presentation.reservation.booking.model.ScreeningMovieUiModel
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs

interface MovieReservationView {
    fun showMovieReservation(reservation: ScreeningMovieUiModel)

    fun showErrorView()

    fun updateHeadCount(count: Int)

    fun updateTimePicker(times: List<String>)

    fun updateScreenDateAt(position: Int)

    fun updateTimePickerAt(position: Int)

    fun updateDatePicker(dates: List<String>)

    fun navigateToSeatSelection(navArgs: SeatSelectionNavArgs)
}
