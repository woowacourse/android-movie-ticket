package woowacourse.movie.feature.completed

import woowacourse.movie.data.MockTicketRepository
import woowacourse.movie.data.TicketRepository
import woowacourse.movie.feature.reservation.ui.toUiModel

class ReservationCompletedPresenter(
    private val view: ReservationCompletedContract.View,
    private val repository: TicketRepository = MockTicketRepository,
) :
    ReservationCompletedContract.Presenter {
    override fun fetchReservationDetails(id: Long) {
        val ticket = repository.find(id) ?: return
        view.initializeReservationDetails(ticket.toUiModel())
    }
}
