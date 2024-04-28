package woowacourse.movie.feature.reservation

import android.util.Log
import woowacourse.movie.data.MockScreeningRepository
import woowacourse.movie.data.ScreeningRepository
import woowacourse.movie.domain.reservation.Quantity
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.feature.main.ui.toUiModel
import woowacourse.movie.feature.reservation.ui.toUiModel

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val screeningRepository: ScreeningRepository = MockScreeningRepository,
) :
    ReservationContract.Presenter {
    private val quantity: Quantity = Quantity(0)
    private lateinit var screening: Screening

    override fun fetchScreeningDetails(screeningId: Long) {
        screening = screeningRepository.find(screeningId) ?: return
        view.initializeMovieDetails(screening.toUiModel())
        view.setupScreeningSchedulesControls(screening.schedule.toUiModel())
    }

    fun increaseQuantity() {
        quantity.increase()
        view.updateTicketQuantity(quantity.value)
    }

    fun decreaseQuantity() {
        quantity.decrease()
        view.updateTicketQuantity(quantity.value)
    }
}
