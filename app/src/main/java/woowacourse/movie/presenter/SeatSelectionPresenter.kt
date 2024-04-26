package woowacourse.movie.presenter

import android.widget.TextView
import woowacourse.movie.model.BoxOffice
import woowacourse.movie.model.Count
import woowacourse.movie.model.MovieData
import woowacourse.movie.model.Result
import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.ticketing.BookingDateTime
import woowacourse.movie.model.ticketing.BookingSeat
import woowacourse.movie.presenter.contract.SeatSelectionContract

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
) : SeatSelectionContract.Presenter {
    private lateinit var boxOffice: BoxOffice

    val selectedSeats: List<BookingSeat>
        get() = boxOffice.seats

    val dateTime: BookingDateTime
        get() = boxOffice.bookingDateTime

    override fun initializeSeats(
        screeningId: Long,
        numOfTickets: Int,
        date: String?,
        time: String?,
    ) {
        if (date == null || time == null) {
            view.showToastMessage("상영 시간 정보가 비어있습니다.")
            return
        }
        boxOffice = BoxOffice(Count(numOfTickets), BookingDateTime.of(date, time))
        when (val screening = MovieData.findScreeningDataById(screeningId)) {
            is Result.Success -> {
                println(screening.data.theater.theaterSize)
                println(screening.data.theater.rowClassInfo)
                val theater = screening.data.theater
                view.initializeSeatTable(theater.theaterSize, theater.rowClassInfo)
            }

            is Result.Error -> {
                view.showToastMessage(screening.message)
            }
        }
    }

    override fun addSeat(
        // TODO 안드로이드 의존성 없애기
        textView: TextView,
        row: Int,
        column: Int,
        seatClass: SeatClass,
    ) {
        val selectedSeat = BookingSeat(row, column, seatClass)
        val newSeats = boxOffice.seats + selectedSeat
        boxOffice.updateSeats(newSeats)
        try {
            view.selectSeat(textView, row, column, seatClass)
            updateBottomBarViews()
        } catch (e: IllegalStateException) {
            view.showToastMessage(e.message.toString())
        }
    }

    override fun removeSeat(
        textView: TextView,
        row: Int,
        column: Int,
        seatClass: SeatClass,
    ) {
        val selectedSeat = BookingSeat(row, column, seatClass)
        val newSeats = boxOffice.seats - selectedSeat
        boxOffice.updateSeats(newSeats)
        try {
            view.cancelSeat(textView, row, column, seatClass)
            updateBottomBarViews()
        } catch (e: IllegalStateException) {
            view.showToastMessage(e.message.toString())
        }
    }

    private fun updateBottomBarViews() {
        view.updateTotalPrice(boxOffice.totalPrice)
        view.updateButtonStatus(boxOffice.isSubmitAvailable)
    }

    override fun makeReservation(
        screeningId: Long,
        count: Int,
    ) {
        view.navigateToResultScreen(
            screeningId = screeningId,
            count = count,
            seats = boxOffice.seats.map { "${it.row}${it.column}" }.toTypedArray(),
            totalPrice = boxOffice.totalPrice,
        )
    }
}
