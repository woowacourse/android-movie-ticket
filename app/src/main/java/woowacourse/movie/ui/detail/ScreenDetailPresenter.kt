package woowacourse.movie.ui.detail

import woowacourse.movie.domain.model.IScreen
import woowacourse.movie.domain.model.Image
import woowacourse.movie.domain.model.NullScreen
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Ticket
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
                val screenDetailUI = screen.toDetailUI(movieRepository.imageSrc(screen.movie.id))
                view.showScreen(screenDetailUI)
            }

            is NullScreen -> {
                when (screen.throwable) {
                    is NoSuchElementException -> view.goToBack(screen.throwable)
                    else -> view.unexpectedFinish(screen.throwable)
                }
            }
        }
    }

    override fun loadTicket() {
        view.showTicket(ticket.count)
    }

    override fun saveTicket(count: Int) {
        ticket = Ticket(count)
    }

    private fun screen(id: Int): IScreen {
        screenRepository.findById(id = id).onSuccess { screen ->
            return screen
        }.onFailure { e ->
            return NullScreen(throwable = e)
        }
        return NullScreen()
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

private fun IScreen.toDetailUI(image: Image<Any>) =
    ScreenDetailUI(
        id = id,
        movieDetailUI =
            MovieDetailUI(
                title = movie.title,
                runningTime = movie.runningTime,
                description = movie.description,
                image = image,
            ),
        date = date,
    )
