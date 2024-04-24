package woowacourse.movie.ui.detail

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.Ticket.Companion.MIN_TICKET_COUNT
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.ui.toDetailUI
import java.lang.IllegalStateException

class ScreenDetailPresenter(
    private val view: ScreenDetailContract.View,
    private val movieRepository: MovieRepository,
    private val screenRepository: ScreenRepository,
    private val reservationRepository: ReservationRepository,
) : ScreenDetailContract.Presenter {
    private var ticket: Ticket = Ticket(MIN_TICKET_COUNT)

    override fun loadScreen(screenId: Int) {
        try {
            view.showScreen(screen(screenId).toDetailUI(movieRepository.imageSrc(screen(screenId).movie.id)))
        } catch (e: Exception) {
            when (e) {
                is NoSuchElementException -> view.goToBack(e)
                else -> view.unexpectedFinish(e)
            }
        }
    }

    override fun loadTicket() {
        view.showTicket(ticket.count)
    }

    override fun saveTicket(count: Int) {
        ticket = Ticket(count)
    }

    private fun screen(id: Int): Screen {
        screenRepository.findById(id = id).onSuccess { screen ->
            return screen
        }.onFailure { e ->
            throw e
        }
        throw IllegalStateException("예기치 못한 오류")
    }

    override fun plusTicket() {
        try {
            ticket = ticket.increase()
            view.showTicket(ticket.count)
        } catch (e: IllegalArgumentException) {
            view.showToastMessage(e)
        }
    }

    override fun minusTicket() {
        try {
            ticket = ticket.decrease()
            view.showTicket(ticket.count)
        } catch (e: IllegalArgumentException) {
            view.showToastMessage(e)
        }
    }

    override fun reserve(screenId: Int) {
        reservationRepository.save(
            screen(screenId),
            ticket.count,
        ).onSuccess { id ->
            view.navigateToReservation(id)
        }.onFailure { e ->
            view.showToastMessage(e)
        }
    }
}
