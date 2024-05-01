package woowacourse.movie.presentation.seat_selection

import woowacourse.movie.model.Reservation
import woowacourse.movie.model.Theater
import woowacourse.movie.model.pricing.TierPricePolicy
import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.model.seat.Position
import woowacourse.movie.repository.movie.MovieRepository
import woowacourse.movie.repository.movie.PseudoMovieRepository
import woowacourse.movie.repository.reservation.PseudoReservationRepository
import woowacourse.movie.repository.reservation.ReservationRepository
import woowacourse.movie.repository.theater.PseudoTheaterRepository
import woowacourse.movie.repository.theater.TheaterRepository
import woowacourse.movie.uimodel.movie.format
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val theaterRepository: TheaterRepository = PseudoTheaterRepository(),
    private val reservationRepository: ReservationRepository = PseudoReservationRepository(),
    private val movieRepository: MovieRepository = PseudoMovieRepository(),
) : SeatSelectionContract.Presenter {
    private lateinit var theater: Theater
    private val selectedPositions: MutableSet<Position> = mutableSetOf()
    private var movieId: Int = -1
    private var ticketNum: Int = -1
    private lateinit var reservedDateTime: LocalDateTime

    override fun loadData(
        movieId: Int,
        ticketNum: Int,
        reservedDateTime: LocalDateTime,
    ) {
        this.movieId = movieId
        this.ticketNum = ticketNum
        this.reservedDateTime = reservedDateTime
        val title = movieRepository.getMovie(this.movieId).title.format()
        theater = theaterRepository.getTheater()
        view.deActivateConfirm()
        updateTicketPrice()
        view.displayMovieTitle(title)
        view.displayTheater(theater)
    }

    override fun toggleSeatSelection(position: Position) {
        if (selectedPositions.contains(position)) {
            deSelectSeat(position)
        } else {
            selectSeat(position)
        }
        updateTicketPrice()
    }

    private fun updateTicketPrice() {
        val price = getTicketPrice()
        view.displayTicketPrice(price)
    }

    private fun getTicketPrice(): Int =
        selectedPositions.sumOf {
            val tier =
                theater.tiers[it]
                    ?: throw IllegalArgumentException("SeatSelectionPresenter: 존재하지 않는 좌석 위치입니다")
            TierPricePolicy(tier).getPrice()
        }

    private fun selectSeat(position: Position) {
        if (selectedPositions.size >= ticketNum) return
        selectedPositions.add(position)
        view.displaySelectedSeat(position)
        checkTicketMax()
    }

    private fun deSelectSeat(position: Position) {
        if (selectedPositions.size == 0) return
        selectedPositions.remove(position)
        view.displayDeSelectedSeat(position)
        view.deActivateConfirm()
    }

    private fun checkTicketMax() {
        if (selectedPositions.size == ticketNum) view.activateConfirm()
    }

    override fun askConfirm() {
        view.displayConfirmDialog()
    }

    override fun purchase() {
        val reservation =
            Reservation(
                movieId,
                ScreeningDateTime(reservedDateTime),
                selectedPositions.toList(),
                getTicketPrice(),
            )
        reservationRepository.putReservation(reservation)
        view.navigateToPurchaseConfirmation()
    }
}
