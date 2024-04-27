package woowacourse.movie.presenter

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

    override fun loadSeats(
        screeningId: Long,
        numOfTickets: Int,
        date: String?,
        time: String?,
        title: String?,
        seats: List<BookingSeat>,
    ) {
        if (date == null || time == null || title == null) {
            view.showToastMessage("상영 시간 정보가 비어있습니다.")
            return
        }
        boxOffice = BoxOffice(Count(numOfTickets), BookingDateTime.of(date, time), seats)
        when (val screening = MovieData.findScreeningDataById(screeningId)) {
            is Result.Success -> {
                val theater = screening.data.theater
                view.initializeSeatTable(
                    theater.theaterSize,
                    theater.rowClassInfo,
                    title,
                    boxOffice.totalPrice,
                    selectedSeats,
                )
            }

            is Result.Error -> {
                view.showToastMessage(screening.message)
            }
        }
    }

    override fun updateSeat(
        row: Int,
        column: Int,
        seatClass: SeatClass,
        columnSize: Int,
    ) {
        val selectedSeat = BookingSeat(row, column, seatClass)
        val isSelected = selectedSeat in selectedSeats
        val newSeats: List<BookingSeat> =
            if (isSelected) {
                boxOffice.seats - selectedSeat
            } else {
                boxOffice.seats + selectedSeat
            }

        when (val updateResult = boxOffice.updateSeats(newSeats)) {
            is Result.Success -> {
                view.toggleSeat(row, column, seatClass, !isSelected, columnSize)
                updateBottomBarViews()
            }

            is Result.Error -> view.showToastMessage(updateResult.message)
        }
    }

    override fun makeReservation(
        movieId: Long,
        count: Int,
    ) {
        view.navigateToResultScreen(
            movieId = movieId,
            count = count,
            seats = boxOffice.seats,
            totalPrice = boxOffice.totalPrice,
        )
    }

    private fun updateBottomBarViews() {
        view.updateTotalPrice(boxOffice.totalPrice)
        view.updateButtonStatus(boxOffice.isSubmitAvailable)
    }
}
