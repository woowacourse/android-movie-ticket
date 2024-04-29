package woowacourse.movie.presentation.ui.seatselection

import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.model.MessageType.ReservationSuccessMessage
import woowacourse.movie.presentation.model.ReservationInfo

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val repository: ScreenRepository,
    private val reservationRepository: ReservationRepository,
) : SeatSelectionContract.Presenter {
    private var _uiModel: SeatSelectionUiModel = SeatSelectionUiModel()
    val uiModel: SeatSelectionUiModel
        get() = _uiModel

    override fun updateUiModel(reservationInfo: ReservationInfo) {
        _uiModel =
            uiModel.copy(
                id = reservationInfo.screenId,
                dateTime = reservationInfo.dateTime,
                ticketCount = reservationInfo.ticketCount,
            )
    }

    override fun loadScreen(id: Int) {
        repository.findByScreenId(id = id).onSuccess { screen ->
            _uiModel = uiModel.copy(screen = screen)
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
            view.initClickListener(seatBoard.seats)
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

        if (seat in uiModel.userSeat.seats) {
            _uiModel = uiModel.copy(userSeat = uiModel.userSeat.removeAt(seat))
            view.unselectSeat(column, row)
            return
        }
        if (uiModel.userSeat.seats.size == uiModel.ticketCount) {
            view.showSnackBar(MessageType.AllSeatsSelectedMessage(uiModel.ticketCount))
        } else {
            view.selectSeat(column, row)
            _uiModel = _uiModel.copy(userSeat = _uiModel.userSeat + seat)
        }
    }

    override fun calculateSeat() {
        var newPrice = 0
        uiModel.userSeat.seats.forEach { seat ->
            newPrice += seat.seatRank.price
        }

        _uiModel = uiModel.copy(totalPrice = newPrice)
        view.showTotalPrice(uiModel.totalPrice)
    }

    override fun checkAllSeatsSelected() {
        view.buttonEnabled(uiModel.userSeat.seats.size == uiModel.ticketCount)
    }

    override fun reserve() {
        uiModel.screen?.let { screen ->
            uiModel.dateTime?.let { dateTime ->
                reservationRepository.saveReservation(
                    screen.movie,
                    uiModel.ticketCount,
                    uiModel.userSeat.seats,
                    dateTime,
                ).onSuccess { id ->
                    view.showToastMessage(ReservationSuccessMessage)
                    view.navigateToReservation(id)
                }.onFailure { e ->
                    view.showSnackBar(e)
                    view.back()
                }
            }
        }
    }

    private fun String.toColumnIndex(): Int = this[0].code - 'A'.code
}
