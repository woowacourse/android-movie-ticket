package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.presentation.reservation.booking.model.MovieReservationUiState
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs
import woowacourse.movie.repository.MovieRepository

class MovieReservationPresenter(
    private val view: MovieReservationView,
    private val repository: MovieRepository,
) {
    private var _uiState: MovieReservationUiState = MovieReservationUiState()
    val uiState: MovieReservationUiState
        get() = _uiState

    fun loadScreenMovie(id: Long) {
        val screenMovie = repository.screenMovieById(id) ?: return view.showErrorView()
        _uiState = screenMovie.toReservationUiState()
        updateReservationView()
    }

    fun updateScreenDateAt(position: Int) {
        _uiState = _uiState.copy(selectedDate = _uiState.screenDateTimes[position])
        view.showTimePicker(_uiState.screenTimes)
    }

    fun updateScreenTimeAt(position: Int) {
        _uiState = _uiState.copy(selectedTime = _uiState.screenTimes[position])
    }

    fun plusCount() {
        val newCount = _uiState.headCount.increase().count
        _uiState = _uiState.copy(count = newCount)
        view.showHeadCount(newCount)
    }

    fun minusCount() {
        val count = _uiState.headCount
        if (count.canDecrease()) {
            val newCount = _uiState.headCount.decrease().count
            _uiState = _uiState.copy(count = newCount)
            view.showHeadCount(newCount)
        }
    }

    fun restoreState(state: MovieReservationUiState) {
        _uiState = state
        updateReservationView()
        view.showTimePickerAt(_uiState.selectedTimePosition)
        view.showScreenDateAt(_uiState.selectedDatePosition)
    }

    fun completeReservation() {
        val selectedTime = _uiState.selectedTime
        val selectedDate = _uiState.selectedDate.date
        val reservedDateTime = parseScreenDateTime(selectedDate, selectedTime)
        val seatSelectionNavArgs =
            SeatSelectionNavArgs(
                _uiState.id,
                _uiState.movie.title,
                _uiState.headCount.count,
                reservedDateTime,
            )
        view.navigateToSeatSelection(seatSelectionNavArgs)
    }

    private fun updateReservationView() {
        view.showMovieReservation(_uiState.movie)
        view.showDatePicker(_uiState.screenDates)
        view.showTimePicker(_uiState.screenTimes)
        view.showHeadCount(_uiState.headCount.count)
    }
}
