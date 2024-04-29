package woowacourse.movie.ui.reservation

import woowacourse.movie.domain.repository.ReservationRepository

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val repository: ReservationRepository,
) : ReservationContract.Presenter {
    override fun loadReservation2(reservation2Id: Int) {
        repository.findById2(reservation2Id)
            .onSuccess {
                view.showReservation2(it)
            }
            .onFailure { e ->
                when (e) {
                    is NoSuchElementException -> view.goToBack("해당하는 상영 정보가 없습니다.")
                    else -> view.unexpectedFinish("예상치 못한 에러가 발생했습니다")
                }
            }
    }
}
