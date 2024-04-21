package woowacourse.movie.ui.detail

import android.util.Log
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Screen2
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.Ticket.Companion.MAX_TICKET_COUNT
import woowacourse.movie.domain.model.Ticket.Companion.MIN_TICKET_COUNT
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ReservationRepository2
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.domain.repository.ScreenRepository2
import woowacourse.movie.ui.MovieDetailUI
import woowacourse.movie.ui.ScreenDetailUI

class ScreenDetailPresenter(
    private val view: ScreenDetailContract.View,
    private val screenRepository: ScreenRepository,
    private val reservationRepository: ReservationRepository,
) : ScreenDetailContract.Presenter {
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

    override fun reserve() {
        reservationRepository.save(screen, ticket.count).onSuccess { id ->
            view.navigateToReservation(id)
        }.onFailure { e ->
            view.unexpectedFinish("예상치 못한 에러가 발생했습니다")
        }
    }
}

class ScreenDetailPresenter2(
    private val view: ScreenDetailContract2.View,
    private val movieRepository: MovieRepository,
    private val screenRepository: ScreenRepository2,
    private val reservationRepository: ReservationRepository2,
) : ScreenDetailContract2.Presenter {
    private var ticket: Ticket = Ticket(MIN_TICKET_COUNT)
    private lateinit var screen: Screen2

    override fun loadScreen(id: Int) {
        screenRepository.findById(id = id).onSuccess { screen ->
            this.screen = screen

            val screenDetailUI =
                ScreenDetailUI(
                    id = screen.id,
                    movieDetailUI =
                        MovieDetailUI(
                            title = screen.movie.title,
                            runningTime = screen.movie.runningTime,
                            description = screen.movie.description,
                            image = movieRepository.imageSrc(screen.movie.id),
                        ),
                    date = screen.date,
                )

            view.showScreen(screenDetailUI)
            view.showTicket(ticket.count)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> view.goToBack("해당하는 상영 정보가 없습니다.")
                else -> view.unexpectedFinish("예상치 못한 에러가 발생했습니다")
            }
        }
    }

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

    override fun reserve() {
        reservationRepository.save(screen, ticket.count).onSuccess { id ->
            view.navigateToReservation(id)
        }.onFailure { e ->
            Log.e("ScreenDetailPresenter2", "예상치 못한 에러가 발생했습니다 : ${e.message}")
            view.showToastMessage("예상치 못한 에러가 발생했습니다 : ${e.message}")
        }
    }
}
