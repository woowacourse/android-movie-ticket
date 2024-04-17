package woowacourse.movie.ui.detail

import woowacourse.movie.domain.repository.ScreenRepository

class DetailViewModel(private val repository: ScreenRepository) {
    private var ticketCount: Int = MIN_TICKET_COUNT

    fun loadScreen(id: Int): DetailEventState {
        repository.findById(id = id).onSuccess { screen ->
            return DetailEventState.Success.ScreenLoading(screen)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> {
                    return DetailEventState.Failure.GoToBack("해당하는 상영 정보가 없습니다.")
                }
            }
        }

        return DetailEventState.Failure.UnexpectedFinish("예상치 못한 에러가 발생했습니다")
    }

    fun plusTicket(): DetailEventState {
        if (ticketCount >= MAX_TICKET_COUNT) {
            return DetailEventState.Failure.ShowToastMessage("티켓 수량은 10개 이하이어야 합니다.")
        }
        return DetailEventState.Success.UpdateTicket(++ticketCount)
    }

    fun minusTicket(): DetailEventState {
        if (ticketCount <= MIN_TICKET_COUNT) {
            return DetailEventState.Failure.ShowToastMessage("티켓 수량은 1개 이상이어야 합니다.")
        }
        return DetailEventState.Success.UpdateTicket(--ticketCount)
    }

    fun clickReservationDone(): DetailEventState = DetailEventState.Success.NavigateToReservation

    companion object {
        private const val MAX_TICKET_COUNT = 10
        private const val MIN_TICKET_COUNT = 1
    }
}
