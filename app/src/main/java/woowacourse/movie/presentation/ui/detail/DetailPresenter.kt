package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.Ticket.Companion.MAX_TICKET_COUNT
import woowacourse.movie.domain.model.Ticket.Companion.MIN_TICKET_COUNT
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.presentation.model.MessageType

class DetailPresenter(
    private val view: DetailContract.View,
    private val screenRepository: ScreenRepository,
    private val reservationRepository: ReservationRepository,
) : DetailContract.Presenter {
    private var ticket: Ticket = Ticket(MIN_TICKET_COUNT)
    private lateinit var screen: Screen

    override fun loadScreen(id: Int) {
        screenRepository.findByScreenId(id = id).onSuccess { screen ->
            this.screen = screen
            view.showScreen(screen)
            view.showTicket(ticket.count)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> {
                    view.showToastMessage(e)
                    view.back()
                }

                else -> {
                    view.showSnackBar(e)
                    view.back()
                }
            }
        }
    }

    override fun plusTicket() {
        val nextTicket = ticket.increase(1)

        if (nextTicket.isInvalidCount()) {
            view.showToastMessage(MessageType.TicketMaxCountMessage(MAX_TICKET_COUNT))
            return
        }
        ticket = nextTicket
        view.showTicket(ticket.count)
    }

    override fun minusTicket() {
        val nextTicket = ticket.decrease(-1)

        if (nextTicket.isInvalidCount()) {
            view.showToastMessage(MessageType.TicketMinCountMessage(MIN_TICKET_COUNT))
            return
        }
        ticket = nextTicket
        view.showTicket(ticket.count)
    }

    override fun reserve() {
        reservationRepository.saveReservation(screen, ticket.count).onSuccess { id ->
            view.navigateToReservation(id)
        }.onFailure { e ->
            view.showToastMessage(e)
            view.back()
        }
    }
}
