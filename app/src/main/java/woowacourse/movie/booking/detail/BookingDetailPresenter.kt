package woowacourse.movie.booking.detail

import android.os.Bundle
import woowacourse.movie.model.BookingResult
import woowacourse.movie.model.Booking
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.LocalTime

class BookingDetailPresenter(
    private val view: BookingDetailContract.View,
    private val movie: Movie?,
) : BookingDetailContract.Presenter {
    private lateinit var bookingResult: BookingResult
    private lateinit var booking: Booking

    override fun initializeData(savedInstanceState: Bundle?) {
        if (movie == null) {
            view.showToastErrorAndFinish("영화 정보를 불러올 수 없습니다.")
            return
        }

        bookingResult = restoreData(movie, savedInstanceState)
        booking = Booking(movie)

        view.showMovieInfo(movie)
        view.showBookingResult(bookingResult)
        view.showScreeningDates(
            dates = booking.screeningPeriods(),
            selected = bookingResult.selectedDate,
        )
        view.showScreeningTimes(
            times = booking.screeningTimes(bookingResult.selectedDate),
            selected = bookingResult.selectedTime,
        )
    }

    override fun onDateSelected(date: LocalDate) {
        bookingResult = bookingResult.updateDate(date)
        val times = booking.screeningTimes(date)

        if (times.isEmpty()) {
            val nextDate = date.plusDays(1)
            bookingResult = bookingResult.updateDate(nextDate)
            val nextTimes = booking.screeningTimes(nextDate)
            nextTimes.firstOrNull()?.let {
                bookingResult = bookingResult.updateTime(it)
            }
            view.showScreeningTimes(nextTimes, bookingResult.selectedTime)
        } else {
            bookingResult = bookingResult.updateTime(times.first())
            view.showScreeningTimes(times, bookingResult.selectedTime)
        }

        view.showBookingResult(bookingResult)
    }

    override fun onTimeSelected(time: LocalTime) {
        if (bookingResult.selectedTime == time) return

        bookingResult = bookingResult.updateTime(time)
        view.showScreeningTimes(booking.screeningTimes(bookingResult.selectedDate), bookingResult.selectedTime)
        view.showBookingResult(bookingResult)
    }

    override fun onHeadCountIncreased() {
        bookingResult = bookingResult.plusHeadCount()
        view.showBookingResult(bookingResult)
    }

    override fun onHeadCountDecreased() {
        if (bookingResult.isHeadCountValid()) bookingResult = bookingResult.minusHeadCount()
        view.showBookingResult(bookingResult)
    }

    override fun onConfirmReservation() {
        if (bookingResult.isHeadCountValid()) {
            view.showBookingResultDialog(bookingResult)
        }
    }

    override fun onSaveState(outState: Bundle) {
        outState.putInt(KEY_HEAD_COUNT, bookingResult.headCount)
        outState.putString(KEY_SCREENING_DATE, bookingResult.selectedDate.toString())
        outState.putString(KEY_SCREENING_TIME, bookingResult.selectedTime.toString())
    }

    private fun restoreData(
        movie: Movie,
        savedInstanceState: Bundle?,
    ): BookingResult {
        val savedCount = savedInstanceState?.getInt(KEY_HEAD_COUNT)
        val savedScreeningDate = savedInstanceState?.getString(KEY_SCREENING_DATE)
        val savedScreeningTime = savedInstanceState?.getString(KEY_SCREENING_TIME)

        val date = savedScreeningDate?.let { LocalDate.parse(it) } ?: LocalDate.now()
        val time = savedScreeningTime?.let { LocalTime.parse(it) } ?: LocalTime.now()

        val headCount = savedCount ?: 0
        return BookingResult(movie.title, headCount, date, time)
    }

    companion object {
        private const val KEY_HEAD_COUNT = "HEAD_COUNT"
        private const val KEY_SCREENING_DATE = "SCREENING_DATE"
        private const val KEY_SCREENING_TIME = "SCREENING_TIME"
    }
}