package woowacourse.movie.presentation.seatSelection

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.Movie
import woowacourse.movie.model.SeatingSystem
import woowacourse.movie.model.Ticket

class SeatSelectionPresenter(
    private val seatSelectionContractView: SeatSelectionContract.View,
    ticketCount: Int,
    private val movieRepository: MovieRepository = MovieRepository(),
) : SeatSelectionContract.Presenter {
    private lateinit var selectedMovie: Movie
    val seatingSystem = SeatingSystem(ticketCount)

    override fun loadMovieData(id: Int) {
        movieRepository.findMovieById(id)
            .onSuccess { movie ->
                selectedMovie = movie
                seatSelectionContractView.displayTicketInfo(movie)
                updateUI()
            }
            .onFailure {
                seatSelectionContractView.showToastMessage(it.message)
            }
    }

    override fun loadSeats() {
        seatSelectionContractView.displaySeats(seatingSystem.seats)
    }

    override fun updateSeatSelection(index: Int) {
        if (seatingSystem.isSelected(index)) {
            unSelectSeat(index)
        } else {
            selectSeat(index)
        }
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

    override fun navigate(screeningDateTime: String) {
        val ticket = Ticket(selectedMovie.title, screeningDateTime, seatingSystem.selectedSeats.toList())
        seatSelectionContractView.navigate(ticket)
    }
}
