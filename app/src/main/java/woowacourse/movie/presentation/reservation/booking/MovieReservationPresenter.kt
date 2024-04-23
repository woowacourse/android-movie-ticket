package woowacourse.movie.presentation.reservation.booking

import woowacourse.movie.model.HeadCount
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDate
import kotlin.properties.Delegates

class MovieReservationPresenter(
    private val view: MovieReservationView,
    private val repository: MovieRepository,
    initialCount: Int = DEFAULT_COUNT,
) {
    private lateinit var date: LocalDate
    private var id by Delegates.notNull<Long>()
    private var count: HeadCount = HeadCount(initialCount)

    fun loadScreenMovie(id: Long) {
        repository.screenMovieById(id).onSuccess { movie ->
            this.id = id
            date = movie.screenDateTimes.first().date
            view.showMovieReservation(movie.toMovieReservationUiModel())
            view.updateHeadCount(count.count)
        }.onFailure {
            view.showErrorView()
        }
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
        val dateTime = date.atTime(0, 0, 0)
        repository.reserveMovie(id, dateTime = dateTime, count = count).onSuccess {
            view.navigateToReservationResultView(it)
        }
    }

    companion object {
        private const val DEFAULT_COUNT = 1
    }
}
