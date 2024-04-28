package woowacourse.movie.presenter

import woowacourse.movie.contract.SeatSelectionContract
import woowacourse.movie.model.Theater
import woowacourse.movie.model.seat.Position
import woowacourse.movie.repository.TheaterRepository

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val theaterRepository: TheaterRepository,
    private val ticketNum: Int,
) : SeatSelectionContract.Presenter {
    private lateinit var theater: Theater
    private val selectedPositions: MutableSet<Position> = mutableSetOf()

    override fun loadTheater() {
        theater = theaterRepository.getTheater()
        view.displayTheater(theater)
    }

    override fun toggleSeatSelection(position: Position) {
        if (selectedPositions.contains(position)) {
            deSelectSeat(position)
        } else {
            selectSeat(position)
        }
    }

    private fun selectSeat(position: Position) {
        if (selectedPositions.size >= ticketNum) return
        selectedPositions.add(position)
        view.displaySelectedSeat(position)
        checkConfirm()
    }

    private fun deSelectSeat(position: Position) {
        if (selectedPositions.size == 0) return
        selectedPositions.remove(position)
        view.displayDeSelectedSeat(position)
        view.deActivateConfirm()
    }

    private fun checkConfirm() {
        if (selectedPositions.size == ticketNum) view.activateConfirm()
    }
}
