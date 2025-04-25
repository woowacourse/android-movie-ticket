package woowacourse.movie.booking.detail

import woowacourse.movie.booking.complete.BookingCompleteUiModel
import woowacourse.movie.domain.DateType
import woowacourse.movie.domain.TicketInfo
import woowacourse.movie.domain.TicketQuantity
import woowacourse.movie.movies.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime

class BookingDetailPresenter(
    private val view: BookingDetailContract.View,
) : BookingDetailContract.Presenter {
    private lateinit var movie: MovieUiModel
    private lateinit var ticketInfo: TicketInfo
    private var selectedDate: LocalDate? = null
    private var selectedTime: LocalTime? = null
    private var ticketQuantity: TicketQuantity = TicketQuantity(1)

    override fun loadMovie(movieUiModel: MovieUiModel) {
        this.movie = movieUiModel
        view.showMovieInfo(movieUiModel)
    }

    override fun loadTicketCount() {
        view.showTicketCount(ticketQuantity.value)
    }

    override fun setTicketCount(count: Int) {
        ticketQuantity = TicketQuantity(count)
        view.showTicketCount(ticketQuantity.value)
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
        ticketQuantity = ticketQuantity.increase()
        view.showTicketCount(ticketQuantity.value)
    }

    override fun onDecreaseTicketCount() {
        ticketQuantity = ticketQuantity.decrease()
        view.showTicketCount(ticketQuantity.value)
    }

    override fun onSelectComplete() {
        val selectedDate = selectedDate
        val selectedTime = selectedTime

        if (selectedDate == null || selectedTime == null) return

        val ticketInfo =
            TicketInfo(
                movieTitle = movie.title,
                date = selectedDate,
                time = selectedTime,
                quantity = ticketQuantity,
            )

        val uiModel =
            BookingCompleteUiModel(
                title = ticketInfo.movieTitle,
                date = ticketInfo.date.toString(),
                time = ticketInfo.time.toString(),
                ticketQuantity = ticketInfo.quantity.value,
                ticketTotalPrice = ticketInfo.quantity.totalPrice(),
            )

        view.showConfirmationDialog(uiModel)
    }

    override fun getTicketCount(): Int = ticketQuantity.value

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
