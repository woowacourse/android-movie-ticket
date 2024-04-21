package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ReservationRepository2

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val repository: ReservationRepository,
) : ReservationContract.Presenter {
    override fun loadReservation(id: Int) {
        repository.findById(id).onSuccess { screen ->
            view.showReservation(screen)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> view.goToBack("해당하는 상영 정보가 없습니다.")
                else -> view.unexpectedFinish("예상치 못한 에러가 발생했습니다")
            }
        }
    }
}

class ReservationPresenter2(
    private val view: ReservationContract2.View,
    private val repository: ReservationRepository2,
) : ReservationContract2.Presenter {
    override fun loadReservation(id: Int) {
        repository.findById(id).onSuccess { reservation ->
            view.showReservation(reservation)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> view.goToBack("해당하는 상영 정보가 없습니다.")
                else -> view.unexpectedFinish("예상치 못한 에러가 발생했습니다")
            }
        }
    }
}
