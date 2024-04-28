package woowacourse.movie.presenter

import woowacourse.movie.contract.SeatSelectionContract
import woowacourse.movie.model.Theater
import woowacourse.movie.model.pricing.TierPricePolicy
import woowacourse.movie.model.seat.Position
import woowacourse.movie.repository.PseudoReservationRepository
import woowacourse.movie.repository.ReservationRepository
import woowacourse.movie.repository.TheaterRepository
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val theaterRepository: TheaterRepository,
    private val reservationRepository: ReservationRepository = PseudoReservationRepository(),
) : SeatSelectionContract.Presenter {
    private lateinit var theater: Theater
    private val selectedPositions: MutableSet<Position> = mutableSetOf()
    private var movieId: Int = -1
    private var ticketNum: Int = -1
    private lateinit var reservedDateTime: LocalDateTime

    override fun loadData(movieId: Int, ticketNum: Int, reservedDateTime: LocalDateTime) {
        this.movieId
        this.ticketNum = ticketNum
        this.reservedDateTime = reservedDateTime
        theater = theaterRepository.getTheater()
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

    private fun getTicketPrice(): Int = selectedPositions.sumOf {
        val tier = theater.tiers[it]
            ?: throw IllegalArgumentException("SeatSelectionPresenter: 존재하지 않는 좌석 위치입니다")
        TierPricePolicy(tier).getPrice()
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

    override fun askConfirm() {
        view.displayConfirmDialog()
    }

    override fun purchase() {
        view.navigateToPurchaseConfirmation()
    }
}
