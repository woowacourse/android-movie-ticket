package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.presentation.reservation.booking.model.MovieReservationUiState
import woowacourse.movie.repository.MovieRepository

class MovieReservationPresenter(
    private val view: MovieReservationView,
    private val repository: MovieRepository,
) {
    private var _uiState: MovieReservationUiState = MovieReservationUiState()
    val uiState: MovieReservationUiState
        get() = _uiState


    fun loadScreenMovie(id: Long) {
        repository.screenMovieById(id).onSuccess { movie ->
            _uiState = movie.toReservationUiState()
            view.showMovieReservation(_uiState.movie)
            view.updateDatePicker(_uiState.screenDates)
            view.updateTimePicker(_uiState.screenTimes)
            view.updateHeadCount(_uiState.headCount.count)
        }.onFailure {
            view.showErrorView()
        }
    }

    fun updateScreenDateAt(position: Int) {
        _uiState = _uiState.copy(selectedDate = _uiState.screenDateTimes[position])
        view.updateTimePicker(_uiState.screenTimes)
    }

    fun updateScreenTimeAt(position: Int) {
        _uiState = _uiState.copy(selectedTime = _uiState.screenTimes[position])
    }

    fun plusCount() {
        val newCount = _uiState.headCount.increase().count
        _uiState = _uiState.copy(count = newCount)
        view.updateHeadCount(newCount)
    }

    fun minusCount() {
        val count = _uiState.headCount
        if (count.canDecrease()) {
            val newCount = _uiState.headCount.decrease().count
            _uiState = _uiState.copy(count = newCount)
            view.updateHeadCount(newCount)
        }
    }

    fun restoreState(state: MovieReservationUiState) {
        _uiState = state
        view.showMovieReservation(_uiState.movie)
        view.updateDatePicker(_uiState.screenDates)
        view.updateTimePicker(_uiState.screenTimes)
        view.updateTimePickerAt(_uiState.selectedTimePosition)
        view.updateScreenDateAt(_uiState.selectedDatePosition)
        view.updateHeadCount(_uiState.headCount.count)
    }

    fun completeReservation() {
        val id = _uiState.id
        val headCount = _uiState.headCount
        val selectedTime = _uiState.selectedTime
        val selectedDate = _uiState.selectedDate.date
        val reservedDateTime = parseScreenDateTime(selectedDate, selectedTime)
        repository.reserveMovie(id, dateTime = reservedDateTime, count = headCount).onSuccess {
            view.navigateToReservationResultView(it)
        }
    }
}