package woowacourse.movie.presentation.ui.reservation

import woowacourse.movie.domain.repository.ReservationRepository

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val repository: ReservationRepository,
) : ReservationContract.Presenter {
    override fun loadReservation(id: Int) {
        repository.findByReservationId(id).onSuccess { screen ->
            view.showReservation(screen)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> {
                    view.showToastMessage(e)
                    view.back()
                }

                else -> {
                    view.showToastMessage(e)
                    view.back()
                }
            }
        }
    }
}
