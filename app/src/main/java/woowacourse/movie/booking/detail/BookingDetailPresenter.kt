package woowacourse.movie.booking.detail

import android.os.Bundle
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.Booking
import woowacourse.movie.model.HeadCount
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import woowacourse.movie.movie.MovieUiModel
import java.time.LocalDate
import java.time.LocalTime

class BookingDetailPresenter(
    private val view: BookingDetailContract.View,
    private val movie: MovieUiModel?,
) : BookingDetailContract.Presenter {
    private lateinit var ticket: Ticket
    private lateinit var booking: Booking

    override fun initializeData(savedInstanceState: Bundle?) {
        if (movie == null) {
            view.showToastErrorAndFinish("영화 정보를 불러올 수 없습니다.")
            return
        }

        ticket = restoreData(movie, savedInstanceState)
        booking = Booking(movie.toDomain())

        view.showMovieInfo(movie)
        view.showTicket(ticket.toUiModel())
        view.showScreeningDates(
            dates = booking.screeningPeriods(),
            selected = ticket.selectedDate,
        )
        view.showScreeningTimes(
            times = booking.screeningTimes(ticket.selectedDate),
            selected = ticket.selectedTime,
        )
    }

    override fun onDateSelected(date: LocalDate) {
        ticket = ticket.updateDate(date)
        val times = booking.screeningTimes(date)

        if (times.isEmpty()) {
            val nextDate = date.plusDays(1)
            ticket = ticket.updateDate(nextDate)
            val nextTimes = booking.screeningTimes(nextDate)
            nextTimes.firstOrNull()?.let {
                ticket = ticket.updateTime(it)
            }
            view.showScreeningTimes(nextTimes, ticket.selectedTime)
        } else {
            ticket = ticket.updateTime(times.first())
            view.showScreeningTimes(times, ticket.selectedTime)
        }

        view.showTicket(ticket.toUiModel())
    }

    override fun onTimeSelected(time: LocalTime) {
        if (ticket.selectedTime == time) return

        ticket = ticket.updateTime(time)
        view.showScreeningTimes(booking.screeningTimes(ticket.selectedDate), ticket.selectedTime)
        view.showTicket(ticket.toUiModel())
    }

    override fun onHeadCountIncreased() {
        ticket = ticket.plusHeadCount()
        view.showTicket(ticket.toUiModel())
    }

    override fun onHeadCountDecreased() {
        if (ticket.isHeadCountValid()) ticket = ticket.minusHeadCount()
        view.showTicket(ticket.toUiModel())
    }

    override fun onConfirmReservation() {
        if (ticket.isHeadCountValid()) {
            view.startSeatSelectionActivity(ticket.toUiModel())
        }
    }

    override fun onSaveState(outState: Bundle) {
        outState.putInt(KEY_HEAD_COUNT, ticket.headCount.value)
        outState.putString(KEY_SCREENING_DATE, ticket.selectedDate.toString())
        outState.putString(KEY_SCREENING_TIME, ticket.selectedTime.toString())
    }

    private fun restoreData(
        movie: MovieUiModel,
        savedInstanceState: Bundle?,
    ): Ticket {
        val savedCount = savedInstanceState?.getInt(KEY_HEAD_COUNT)
        val savedScreeningDate = savedInstanceState?.getString(KEY_SCREENING_DATE)
        val savedScreeningTime = savedInstanceState?.getString(KEY_SCREENING_TIME)

        val date = savedScreeningDate?.let { LocalDate.parse(it) } ?: LocalDate.now()
        val time = savedScreeningTime?.let { LocalTime.parse(it) } ?: LocalTime.now()

        val headCount = savedCount ?: 0
        return Ticket(movie.title, HeadCount(headCount), date, time, Seats(emptyList()))
    }

    companion object {
        private const val KEY_HEAD_COUNT = "HEAD_COUNT"
        private const val KEY_SCREENING_DATE = "SCREENING_DATE"
        private const val KEY_SCREENING_TIME = "SCREENING_TIME"
    }
}
