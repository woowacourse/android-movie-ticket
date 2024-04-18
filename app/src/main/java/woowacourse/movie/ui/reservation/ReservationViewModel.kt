package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.repository.ReservationRepository

class ReservationViewModel(private val repository: ReservationRepository) {
    fun loadReservation(id: Int): ReservationEventState {
        repository.findById(id)
            .onSuccess { screen ->
                return ReservationEventState.Success.ReservationLoading(screen)
            }
            .onFailure { e ->
                when (e) {
                    is NoSuchElementException -> {
                        return ReservationEventState.Failure.GoToBack("해당하는 상영 정보가 없습니다.")
                    }
                }
            }

        return ReservationEventState.Failure.UnexpectedFinish("예상치 못한 에러가 발생했습니다")
    }
}
