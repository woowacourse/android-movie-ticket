package woowacourse.movie.presentation.booking

import woowacourse.movie.domain.model.pricingpolicy.DefaultPricingPolicy
import woowacourse.movie.domain.model.HeadCount
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.movie.MovieScheduler
import woowacourse.movie.domain.model.movie.MovieTicket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingPresenter(
    private val view: BookingContract.View,
    private val movie: Movie
) : BookingContract.Presenter {
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    private var headCount: HeadCount = HeadCount()
    private val movieScheduler: MovieScheduler by lazy { MovieScheduler(movie.startScreeningDate, movie.endScreeningDate) }

    override fun onViewCreated() {
        view.initBooking()
        view.showMovie(movie)
        view.showBookableDates(movieScheduler.getBookableDates())
        view.updateHeadCount(headCount.value)
    }

    override fun onDateSelected(selectedDate: LocalDate) {
        this.selectedDate = selectedDate
        view.showBookableTimes(movieScheduler.getBookableTimes(selectedDate))
    }

    override fun onTimeSelected(selectedTime: LocalTime) {
        this.selectedTime = selectedTime
    }

    override fun onIncreaseHeadCount() {
        headCount.increase()
        view.updateHeadCount(headCount.value)
    }

    override fun onDecreaseHeadCount() {
        headCount.decrease()
        view.updateHeadCount(headCount.value)
    }

    override fun onConfirmClicked() {
        val ticket = MovieTicket(
            title = movie.title,
            screeningDateTime = LocalDateTime.of(selectedDate, selectedTime),
            headCount = headCount.value,
            pricingPolicy = DefaultPricingPolicy()
        )
        view.navigateToBookingSummary(ticket)
    }

    override fun onConfigurationChanged(
        count: Int?,
        date: LocalDate?,
        time: LocalTime?
    ) {
        count?.let { headCount = HeadCount(it) }
        selectedDate = date
        selectedTime = time
        view.updateHeadCount(headCount.value)
    }
}