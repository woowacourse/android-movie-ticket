package woowacourse.movie.booking.detail

import woowacourse.movie.booking.complete.BookingCompleteUiModel
import woowacourse.movie.domain.DateType
import woowacourse.movie.domain.TicketQuantity
import woowacourse.movie.movies.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime

class BookingDetailPresenter(
    private val view: BookingDetailContract.View,
) : BookingDetailContract.Presenter {
    private lateinit var movie: MovieUiModel
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    private var currentTicketCount: TicketQuantity = TicketQuantity(1)

    override fun loadMovie(movieUiModel: MovieUiModel) {
        this.movie = movieUiModel
        view.showMovieInfo(movieUiModel)
    }

    override fun loadTicketCount() {
        view.showTicketCount(currentTicketCount.value)
    }

    override fun loadDateList() {
        val startDate = LocalDate.parse(movie.startDate)
        val endDate = LocalDate.parse(movie.endDate)

        val dates = createDateList(startDate, endDate)
        view.showDateSpinnerItems(dates)
    }

    override fun onDateSelected(date: LocalDate) {
        selectedDate = date
    }

    override fun loadTimeList() {
        val selectedDate = selectedDate ?: return
        val dateType = DateType.from(selectedDate)

        val startHour = if (dateType == DateType.WEEKDAY) 9 else 10
        val times = generateTimeList(startHour)

        view.showTimeSpinnerItems(times)
    }

    override fun onTimeSelected(time: LocalTime) {
        selectedTime = time
    }

    override fun onIncreaseTicketCount() {
        currentTicketCount = currentTicketCount.increase()
        view.showTicketCount(currentTicketCount.value)
    }

    override fun onDecreaseTicketCount() {
        currentTicketCount = currentTicketCount.decrease()
        view.showTicketCount(currentTicketCount.value)
    }

    override fun onSelectComplete() {
        val date = selectedDate
        val time = selectedTime

        if (date == null || time == null) return // validation

        val uiModel =
            BookingCompleteUiModel(
                title = movie.title,
                date = date.toString(),
                time = time.toString(),
                ticketQuantity = currentTicketCount.value,
                ticketTotalPrice = currentTicketCount.totalPrice(),
            )

        view.showConfirmationDialog(uiModel)
    }

    private fun createDateList(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var current = startDate
        while (!current.isAfter(endDate)) {
            dates.add(current)
            current = current.plusDays(1)
        }
        return dates
    }

    private fun generateTimeList(startHour: Int): List<LocalTime> {
        val times = mutableListOf<LocalTime>()
        var current = LocalTime.of(startHour, 0)

        repeat(6) {
            times.add(current)
            current = current.plusHours(2)
        }

        return times
    }
}
