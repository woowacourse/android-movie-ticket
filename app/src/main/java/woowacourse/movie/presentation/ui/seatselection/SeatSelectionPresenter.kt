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
}
