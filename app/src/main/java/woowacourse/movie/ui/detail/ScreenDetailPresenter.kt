package woowacourse.movie.ui.detail

import woowacourse.movie.domain.model.IScreen
import woowacourse.movie.domain.model.NullScreen
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.Ticket.Companion.MAX_TICKET_COUNT
import woowacourse.movie.domain.model.Ticket.Companion.MIN_TICKET_COUNT
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.ui.MovieDetailUI
import woowacourse.movie.ui.ScreenDetailUI

class ScreenDetailPresenter(
    private val view: ScreenDetailContract.View,
    private val movieRepository: MovieRepository,
    private val screenRepository: ScreenRepository,
    private val reservationRepository: ReservationRepository,
) : ScreenDetailContract.Presenter {
    private var ticket: Ticket = Ticket(MIN_TICKET_COUNT)

    override fun loadScreen(screenId: Int) {
        when (val screen = screen(screenId)) {
            is Screen -> {
                val screenDetailUI = screen.toDetailUI()
                view.showScreen(screenDetailUI)
                view.showTicket(ticket.count)
            }

            is NullScreen -> {
                when (screen.throwable) {
                    is NoSuchElementException -> view.goToBack("해당하는 상영 정보가 없습니다.")
                    else -> view.unexpectedFinish("예상치 못한 에러가 발생했습니다")
                }
            }
        }
    }

    private fun screen(id: Int): IScreen {
        screenRepository.findById(id = id).onSuccess { screen ->
            return screen
        }.onFailure { e ->
            return NullScreen(throwable = e)
        }
        return NullScreen()
    }

    private fun IScreen.toDetailUI() =
        ScreenDetailUI(
            id = id,
            movieDetailUI =
                MovieDetailUI(
                    title = movie.title,
                    runningTime = movie.runningTime,
                    description = movie.description,
                    image = movieRepository.imageSrc(movie.id),
                ),
            date = date,
        )

    override fun plusTicket() {
        val increasedTicket = ticket.increase()

        if (increasedTicket.isInvalidCount()) {
            view.showToastMessage("티켓 수량은 ${MAX_TICKET_COUNT}개 이하이어야 합니다.")
            return
        }
        ticket = increasedTicket
        view.showTicket(ticket.count)
    }

    override fun minusTicket() {
        val decreasedTicket = ticket.decrease()

        if (decreasedTicket.isInvalidCount()) {
            view.showToastMessage("티켓 수량은 ${MIN_TICKET_COUNT}개 이상이어야 합니다.")
            return
        }
        ticket = decreasedTicket
        view.showTicket(ticket.count)
    }

    override fun reserve(screenId: Int) {
        reservationRepository.save(
            screen(screenId),
            ticket.count,
        ).onSuccess { id ->
            view.navigateToReservation(id)
        }.onFailure { e ->
            view.showToastMessage("예상치 못한 에러가 발생했습니다 : ${e.message}")
        }
    }
}
