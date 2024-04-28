package woowacourse.movie.presenter

import woowacourse.movie.model.BoxOffice
import woowacourse.movie.model.Count
import woowacourse.movie.model.MovieData
import woowacourse.movie.model.Result
import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.ticketing.BookingSeat
import woowacourse.movie.presenter.contract.SeatSelectionContract
import woowacourse.movie.view.state.TicketingForm
import woowacourse.movie.view.state.TicketingResult

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
) : SeatSelectionContract.Presenter {
    private lateinit var boxOffice: BoxOffice
    lateinit var ticketingResult: TicketingResult
        private set

    override fun loadSeats(
        ticketingForm: TicketingForm,
        seats: List<BookingSeat>,
    ) {
        boxOffice =
            BoxOffice(
                Count(ticketingForm.numberOfTickets.currentValue),
                ticketingForm.bookingDateTime,
                seats,
            )

        when (val screening = MovieData.findScreeningDataById(ticketingForm.screeningId)) {
            is Result.Success -> {
                ticketingResult =
                    TicketingResult(
                        movieTitle = screening.data.movie?.title ?: "",
                        numberOfTickets = ticketingForm.numberOfTickets.currentValue,
                        date = ticketingForm.bookingDateTime.date,
                        time = ticketingForm.bookingDateTime.time,
                        seats = emptyList(),
                        price = boxOffice.totalPrice,
                    )
                val theater = screening.data.theater
                view.initializeSeatTable(
                    theater.theaterSize,
                    theater.rowClassInfo,
                    ticketingForm.movieTitle,
                    boxOffice.totalPrice,
                    boxOffice.seats,
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
        val isSelected = selectedSeat in ticketingResult.seats
        val newSeats: List<BookingSeat> =
            if (isSelected) {
                boxOffice.seats - selectedSeat
            } else {
                boxOffice.seats + selectedSeat
            }

        when (val updateResult = boxOffice.updateSeats(newSeats)) {
            is Result.Success -> {
                ticketingResult =
                    ticketingResult.copy(seats = boxOffice.seats, price = boxOffice.totalPrice)
                view.toggleSeat(row, column, seatClass, !isSelected, columnSize)
                updateBottomBarViews()
            }

            is Result.Error -> view.showToastMessage(updateResult.message)
        }
    }

    override fun makeReservation() {
        view.navigateToResultScreen(ticketingResult)
    }

    private fun updateBottomBarViews() {
        view.updateTotalPrice(boxOffice.totalPrice)
        view.updateButtonStatus(boxOffice.isSubmitAvailable)
    }
}
