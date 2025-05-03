package woowacourse.movie.ui.seat

import woowacourse.movie.domain.movies.MovieRepository
import woowacourse.movie.domain.movies.MovieToReserve
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatPricingPolicy
import woowacourse.movie.domain.seat.SeatService
import woowacourse.movie.domain.seat.SeatState
import woowacourse.movie.domain.ticket.MovieTicketService

class SeatsSelectionPresenter(
    private val view: SeatsSelectionContract.View,
    private val movieToReserve: MovieToReserve,
    private val movieRepository: MovieRepository = MovieRepository(),
    private val movieTicketService: MovieTicketService = MovieTicketService(),
) : SeatsSelectionContract.Presenter {
    private lateinit var seat: Seat
    private val selectedSeats = mutableListOf<Seat>()
    private var amount: Int = 0
    private val pricingPolicy: SeatPricingPolicy = SeatPricingPolicy(selectedSeats)
    private val seatService = SeatService()

    init {
        loadMovieTitle()
        loadAmount()
    }

    override fun onConfirm() {
        val movieTicket =
            movieTicketService.createMovieTicket(movieToReserve, amount, selectedSeats)
        view.navigateToBookingSummary(movieTicket)
    }

    override fun loadMovieTitle() {
        val movie = movieRepository.getMovieById(movieToReserve.movieId)
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
        val seatState = seatService.getSeatSate(seat, movieToReserve.headCount)
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
