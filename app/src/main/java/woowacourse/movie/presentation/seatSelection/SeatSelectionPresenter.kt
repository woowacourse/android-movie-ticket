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
) : SeatSelectionContract.Presenter {
    private val movieRepository = MovieRepository()
    private lateinit var selectedMovie: Movie
    private val seatingSystem = SeatingSystem(ticketCount)

    override fun initializeViewData() {
        seatSelectionContractView.initializeSeats(seatingSystem.seats)
        movieRepository.findMovieById(movieId)
            .onSuccess { movie ->
                selectedMovie = movie
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
            seatSelectionContractView.updateTotalPrice(seatingSystem.getTotalPrice())
            return
        }

        seatingSystem.trySelectSeat(index)
            .onSuccess {
                seatSelectionContractView.updateSelectedSeatUI(index)
                seatSelectionContractView.setButtonEnabledState(!seatingSystem.canSelectSeat())
                seatSelectionContractView.updateTotalPrice(seatingSystem.getTotalPrice())
            }
            .onFailure {
                seatSelectionContractView.showToastMessage(it.message)
            }
    }

    override fun navigate() {
        val ticket = Ticket(selectedMovie.title, screeningDateTime, seatingSystem.selectedSeats.toList())
        seatSelectionContractView.navigate(ticket)
    }
}
