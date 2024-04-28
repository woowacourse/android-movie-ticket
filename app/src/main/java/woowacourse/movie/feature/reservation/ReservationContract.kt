package woowacourse.movie.feature.reservation

import woowacourse.movie.feature.main.ui.ScreeningModel
import woowacourse.movie.feature.reservation.ui.ScreeningScheduleModel

interface ReservationContract {
    interface View {
        fun initializeMovieDetails(movie: ScreeningModel)

        fun setupReservationCompleteControls()

        fun setupTicketQuantityControls()

        fun setupScreeningSchedulesControls(screeningScheduleModel: ScreeningScheduleModel)

        fun updateTicketQuantity(newQuantity: Int)

        fun navigateToCompleteScreen()
    }

    interface Presenter {
        fun fetchScreeningDetails(screeningId: Long)

        fun increaseQuantity()

        fun decreaseQuantity()
    }
}
