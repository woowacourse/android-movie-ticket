package woowacourse.movie.presenter

import woowacourse.movie.domain.model.ReservedMovie
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.policy.PricingPolicy
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.service.MovieTicketService
import woowacourse.movie.domain.service.SeatService
import woowacourse.movie.ui.view.seat.SeatState
import woowacourse.movie.ui.view.seat.SeatsSelectionContract

class SeatsSelectionPresenter(
    private val view: SeatsSelectionContract.View,
    private val reservedMovie: ReservedMovie,
    private val movieRepository: MovieRepository = MovieRepository(),
    private val movieTicketService: MovieTicketService = MovieTicketService(),
) : SeatsSelectionContract.Presenter {
    private lateinit var seat: Seat
    private val selectedSeats = mutableListOf<Seat>()
    private var amount: Int = 0
    private val pricingPolicy: PricingPolicy = PricingPolicy(selectedSeats)
    private val seatService = SeatService()

    init {
        loadMovieTitle()
        loadAmount()
    }

    override fun onConfirm() {
        val movieTicket = movieTicketService.createMovieTicket(reservedMovie, amount, selectedSeats)
        view.navigateToBookingSummary(movieTicket)
    }

    override fun loadMovieTitle() {
        val movie = movieRepository.getMovieById(reservedMovie.movieId)
        view.showMovieTitle(movie.title)
    }

    override fun loadAmount() {
        updateAmount()
        view.showAmount(amount)
    }

    override fun updateAmount() {
        amount = pricingPolicy.calculatePrice()
    }

    override fun onClickSeat(
        row: Int,
        col: Int,
    ) {
        seat = Seat(row, col)
        val seatState = seatService.toggleSeat(seat, reservedMovie.headCount)
        when (seatState) {
            SeatState.SELECTED -> {
                view.changeSeatColor(row, col, true)
                selectedSeats.add(seat)
            }

            SeatState.DESELECTED -> {
                view.changeSeatColor(row, col, false)
                selectedSeats.remove(seat)
            }

            SeatState.LIMIT -> view.showSeatLimitToastMessage()
        }
        loadAmount()
    }
}
