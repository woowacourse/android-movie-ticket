package woowacourse.movie.presentation.ui.seatselection

import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.model.ReservationInfo

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val repository: ScreenRepository,
) : SeatSelectionContract.Presenter {
    private var uiModel: SeatSelectionUiModel = SeatSelectionUiModel()

    override fun updateUiModel(reservationInfo: ReservationInfo) {
        uiModel =
            uiModel.copy(
                id = reservationInfo.screenId,
                dateTime = reservationInfo.dateTime,
                ticketCount = reservationInfo.ticketCount,
            )
    }

    override fun loadScreen(id: Int) {
        repository.findByScreenId(id = id).onSuccess { screen ->
            view.showScreen(screen)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> {
                    view.showToastMessage(e)
                    view.back()
                }

                else -> {
                    view.showToastMessage(e)
                    view.back()
                }
            }
        }
    }

    override fun loadSeatBoard(id: Int) {
        repository.loadSeatBoard(id).onSuccess { seatBoard ->
            view.showSeatBoard(seatBoard.seats)
            view.initClickListener(4, seatBoard.seats)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> {
                    view.showToastMessage(e)
                    view.back()
                }

                else -> {
                    view.showToastMessage(e)
                    view.back()
                }
            }
        }
    }

    override fun clickSeat(seat: Seat) {
        val column = seat.column.toColumnIndex()
        val row = seat.row

        if (seat in uiModel.seats) {
            uiModel.seats.remove(seat)
            view.unselectSeat(column, row)
            return
        }
        if (uiModel.seats.size == uiModel.ticketCount) {
            view.showSnackBar(MessageType.AllSeatsSelectedMessage(uiModel.ticketCount))
        } else {
            view.selectSeat(column, row)
            uiModel.seats.add(seat)
        }
    }

    override fun calculateSeat() {
        var newPrice = 0
        uiModel.seats.forEach { seat ->
            newPrice += seat.column.toSeatPrice()
        }

        uiModel = uiModel.copy(totalPrice = newPrice)
        view.showTotalPrice(uiModel.totalPrice)
    }

    override fun checkAllSeatsSelected() {
        view.buttonEnabled(uiModel.seats.size == uiModel.ticketCount)
    }

    override fun reserve() {}

    private fun String.toColumnIndex(): Int = this[0].code - 'A'.code

    private fun String.toSeatPrice(): Int =
        when (this) {
            "A" -> 10_000
            "B" -> 10_000
            "C" -> 15_000
            "D" -> 15_000
            "E" -> 12_000
            else -> 0
        }
}
