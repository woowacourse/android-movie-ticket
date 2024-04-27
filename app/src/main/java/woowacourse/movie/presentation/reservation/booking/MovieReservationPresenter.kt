package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.ScreenDateTime
import woowacourse.movie.model.ScreenDateTimes
import woowacourse.movie.presentation.screening.formatScreenDates
import woowacourse.movie.presentation.screening.formatScreenTimes
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDate
import java.time.LocalTime
import kotlin.properties.Delegates

data class MovieReservationUiState(
    val id: Long,
    val movie: MovieReservationUiModel,
    val headCount: Int,
    val selectedTime: String,
    val selectedDate: String,
)

class MovieReservationPresenter(
    private val view: MovieReservationView,
    private val repository: MovieRepository,
    initialCount: Int = DEFAULT_COUNT,
) {
    private var id by Delegates.notNull<Long>()
    private lateinit var time: LocalTime
    private lateinit var date: LocalDate
    private lateinit var screenDateTime: ScreenDateTime
    private lateinit var screenDateTimes: ScreenDateTimes
    private var count: HeadCount = HeadCount(initialCount)

    fun loadScreenMovie(id: Long) {
        repository.screenMovieById(id).onSuccess { movie ->
            this.id = id
            screenDateTimes = movie.screenDateTimes
            screenDateTime = screenDateTimes.first()
            view.showMovieReservation(movie.toMovieReservationUiModel())
            view.updateDatePicker(screenDateTimes.localDateTimes().let(::formatScreenDates))
            view.updateTimePicker(formatScreenTimes(screenDateTime.times))
            view.updateHeadCount(count.count)
        }.onFailure {
            view.showErrorView()
        }
    }

    fun updateScreenDateAt(position: Int) {
        date = screenDateTimes.dateTimes[position].date
        screenDateTime = screenDateTimes.screenDateTimeAt(this.date)
        view.updateTimePicker(formatScreenTimes(screenDateTime.times))
        view.updateTimePickerAt(0)
    }

    fun updateScreenTimeAt(position: Int) {
        time = screenDateTime.times[position]
    }

    fun count(): Int = count.count

    fun plusCount() {
        count = count.increase()
        view.updateHeadCount(count.count)
    }

    fun minusCount() {
        if (count.canDecrease()) {
            count = count.decrease()
            view.updateHeadCount(count.count)
        }
    }

    fun completeReservation() {
//        repository.reserveMovie(id, dateTime = dateTime, count = count).onSuccess {
//            view.navigateToReservationResultView(it)
//        }
    }

    companion object {
        private const val DEFAULT_COUNT = 1
    }
}
