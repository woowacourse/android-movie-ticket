package woowacourse.movie.presentation.seatSelection

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.Movie
import woowacourse.movie.model.SeatingSystem
import woowacourse.movie.model.Ticket

class SeatSelectionPresenter(
    private val seatSelectionContractView: SeatSelectionContract.View,
    private val movieId: Int,
    ticketCount: Int,
    private val screeningDateTime: String,
    savedSeats: List<Int>,
) : SeatSelectionContract.Presenter {
    private val movieRepository = MovieRepository()
    private lateinit var selectedMovie: Movie
    val seatingSystem = SeatingSystem(ticketCount, savedSeats)

    override fun initializeViewData() {
        seatSelectionContractView.initializeSeats(seatingSystem.seats, seatingSystem.getSelectedSeatsIndex())
        movieRepository.findMovieById(movieId)
            .onSuccess { movie ->
                selectedMovie = movie
                seatSelectionContractView.initializeTicketInfo(movie)
                updateUI()
            }
            .onFailure {
                seatSelectionContractView.showToastMessage(it.message)
            }
    }

    override fun updateSeatSelection(index: Int) {
        if (seatingSystem.isSelected(index)) {
            unSelectSeat(index)
            return
        }
        selectSeat(index)
    }

    private fun unSelectSeat(index: Int) {
        seatingSystem.unSelectSeat(index)
        seatSelectionContractView.updateUnSelectedSeatUI(index)
        updateUI()
    }

    private fun selectSeat(index: Int) {
        seatingSystem.trySelectSeat(index)
            .onSuccess {
                seatSelectionContractView.updateSelectedSeatUI(index)
                updateUI()
            }
            .onFailure {
                seatSelectionContractView.showToastMessage(it.message)
            }
    }

    private fun updateUI() {
        seatSelectionContractView.setButtonEnabledState(!seatingSystem.canSelectSeat())
        seatSelectionContractView.updateTotalPrice(seatingSystem.getTotalPrice())
    }

    override fun navigate() {
        val ticket = Ticket(selectedMovie.title, screeningDateTime, seatingSystem.selectedSeats.toList())
        seatSelectionContractView.navigate(ticket)
    }
}
