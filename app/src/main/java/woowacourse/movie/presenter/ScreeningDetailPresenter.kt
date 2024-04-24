package woowacourse.movie.presenter

import woowacourse.movie.contract.ScreeningDetailContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.repository.PseudoReservationRepository
import woowacourse.movie.repository.PseudoScreeningRepository
import woowacourse.movie.repository.ReservationRepository
import woowacourse.movie.repository.ScreeningRepository

class ScreeningDetailPresenter(
    private val view: ScreeningDetailContract.View,
    private val screeningRepository: ScreeningRepository = PseudoScreeningRepository(),
    private val reservationRepository: ReservationRepository = PseudoReservationRepository(),
) : ScreeningDetailContract.Presenter {
    override fun loadScreening(screeningId: Int) {
        val screening = screeningRepository.getScreening(screeningId)
        view.displayScreening(screening)
    }

    override fun plusTicketNum(ticketNum: Int) {
        view.displayTicketNum(ticketNum + 1)
    }

    override fun minusTicketNum(ticketNum: Int) {
        if (ticketNum > 0) view.displayTicketNum(ticketNum - 1)
    }

    override fun purchase(
        screeningId: Int,
        ticketNum: Int,
    ) {
        val screening = screeningRepository.getScreening(screeningId)
        val reservation = Reservation(screening, ticketNum)
        val reservationId = reservationRepository.putReservation(reservation)
        // TODO: if it goes fail, view have to notify that something went wrong
        // e.g. view.notifyException()
        view.navigateToPurchaseConfirmation(reservationId)
    }
}
