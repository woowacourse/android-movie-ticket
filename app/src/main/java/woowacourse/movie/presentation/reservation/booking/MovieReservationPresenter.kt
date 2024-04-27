package woowacourse.movie.presentation.reservation.booking

import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.HeadCount
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

@Parcelize
data class MovieReservationUiState(
    val movie: ScreeningMovieUiModel = ScreeningMovieUiModel(),
    val id: Long = -1,
    private val count: Int = 1,
    val screenDateTimes: List<ScreenDateTimeUiModel> = emptyList(),
    val selectedDate: ScreenDateTimeUiModel = ScreenDateTimeUiModel(),
    val selectedTime: String = "",
) : Parcelable {

    val selectedTimePosition: Int
        get() = screenTimes.indexOf(selectedTime)

    val selectedDatePosition: Int
        get() = screenDates.indexOf(selectedDate.date)

    val screenDates: List<String>
        get() = screenDateTimes.map { it.date }

    val screenTimes: List<String>
        get() = selectedDate.times

    @IgnoredOnParcel
    val headCount: HeadCount = HeadCount(count)
}
