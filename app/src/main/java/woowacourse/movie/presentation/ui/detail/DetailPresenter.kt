package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.Ticket.Companion.MAX_TICKET_COUNT
import woowacourse.movie.domain.model.Ticket.Companion.MIN_TICKET_COUNT
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ScreenRepository

class DetailPresenter(
    private val view: DetailContract.View,
    private val screenRepository: ScreenRepository,
    private val reservationRepository: ReservationRepository,
) : DetailContract.Presenter {
    private var ticket: Ticket = Ticket(MIN_TICKET_COUNT)
    private lateinit var screen: Screen

    override fun loadScreen(id: Int) {
        screenRepository.findById(id = id).onSuccess { screen ->
            this.screen = screen
            view.showScreen(screen)
            view.showTicket(ticket.count)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> view.goToBack("해당하는 상영 정보가 없습니다.")
                else -> view.unexpectedFinish("예상치 못한 에러가 발생했습니다")
            }
        }
    }

    override fun plusTicket() {
        val nextTicket = ticket.update(1)

        if (nextTicket.isInvalidCount()) {
            view.showToastMessage("티켓 수량은 ${MAX_TICKET_COUNT}개 이하이어야 합니다.")
            return
        }
        ticket = nextTicket
        view.showTicket(ticket.count)
    }

    override fun minusTicket() {
        val nextTicket = ticket.update(-1)

        if (nextTicket.isInvalidCount()) {
            view.showToastMessage("티켓 수량은 ${MIN_TICKET_COUNT}개 이상이어야 합니다.")
            return
        }
        ticket = nextTicket
        view.showTicket(ticket.count)
    }

    override fun reserve() {
        reservationRepository.save(screen, ticket.count).onSuccess { id ->
            view.navigateToReservation(id)
        }.onFailure { e ->
            view.unexpectedFinish("예상치 못한 에러가 발생했습니다")
        }
    }
}
