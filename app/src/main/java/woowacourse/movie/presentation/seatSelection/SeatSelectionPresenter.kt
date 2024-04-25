package woowacourse.movie.presentation.seatSelection

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.SeatingSystem

class SeatSelectionPresenter(
    private val seatSelectionContractView: SeatSelectionContract.View,
    private val movieId: Int,
    private val ticketCount: Int,
) : SeatSelectionContract.Presenter {
    private val movieRepository = MovieRepository()
    private val seatingSystem = SeatingSystem(ticketCount)

    override fun initializeSeats() {
        seatSelectionContractView.initializeSeats(seatingSystem.seats)
    }

    override fun initializeTicketInfo() {
        movieRepository.findMovieById(movieId)
            .onSuccess { movie ->
                seatSelectionContractView.initializeTicketInfo(movie)
            }
            .onFailure {
                seatSelectionContractView.showToastMessage(it.message)
            }
    }

    override fun updateSeatSelection(index: Int) {
        if (seatingSystem.isSelected(index)) {
            seatingSystem.unSelectSeat(index)
            seatSelectionContractView.updateUnSelectedSeatUI(index)
            seatSelectionContractView.setButtonEnabledState(!seatingSystem.canSelectSeat())
            return
        }

        seatingSystem.trySelectSeat(index)
            .onSuccess {
                seatSelectionContractView.updateSelectedSeatUI(index)
                seatSelectionContractView.setButtonEnabledState(!seatingSystem.canSelectSeat())
            }
            .onFailure {
                seatSelectionContractView.showToastMessage(it.message)
            }
    }
}
