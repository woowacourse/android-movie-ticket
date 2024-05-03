package woowacourse.movie.feature.reservation

import woowacourse.movie.feature.main.ui.ScreeningItem
import woowacourse.movie.feature.reservation.ui.ScreeningScheduleModel

interface ReservationContract {
    interface View {
        fun showMovieDetails(movie: ScreeningItem.ScreeningModel)

        fun showScreeningSchedules(screeningScheduleModel: ScreeningScheduleModel)

        fun updateTicketQuantity(newQuantity: Int)

        fun navigateToCompleteScreen()
    }

    interface Presenter {
        fun fetchScreeningDetails(screeningId: Long)

        fun increaseQuantity()

        fun decreaseQuantity()
    }
}
