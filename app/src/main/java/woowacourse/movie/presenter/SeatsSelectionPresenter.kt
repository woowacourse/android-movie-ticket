package woowacourse.movie.presenter

import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.domain.policy.PricingPolicy
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.ui.view.seat.SeatButtonState
import woowacourse.movie.ui.view.seat.SeatsSelectionContract

class SeatsSelectionPresenter(
    private val view: SeatsSelectionContract.View,
    private val movieTicket: MovieTicket,
) : SeatsSelectionContract.Presenter {
    override fun onConfirm() {
        view.navigateToBookingSummary()
    }

    override fun loadMovieTitle() {
        val movie = MovieRepository().getMovieById(movieTicket.movieId)
        view.showMovieTitle(movie.title)
    }

    override fun loadAmount() {
        updateAmount()
        view.showAmount(movieTicket.amount)
    }

    override fun getSeatResult(seatName: String): SeatButtonState {
        if (movieTicket.selectedSeats.contains(seatName)) {
            movieTicket.selectedSeats.remove(seatName)
            return SeatButtonState.DESELECTED
        }

        if (isSeatLimit()) return SeatButtonState.LIMIT

        movieTicket.selectedSeats.add(seatName)
        return SeatButtonState.SELECTED
    }

    override fun isSeatLimit(): Boolean = movieTicket.selectedSeats.size >= movieTicket.headCount

    override fun updateAmount() {
        movieTicket.amount = PricingPolicy(movieTicket.selectedSeats).calculatePrice()
    }
}
