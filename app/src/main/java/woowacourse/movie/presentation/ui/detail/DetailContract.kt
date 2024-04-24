package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.ScreenDate
import woowacourse.movie.presentation.base.BasePresenter
import woowacourse.movie.presentation.base.BaseView
import woowacourse.movie.presentation.model.ReservationInfo
import java.time.LocalDate
import java.time.LocalTime

interface DetailContract {
    interface View : BaseView {
        fun showScreen(screen: Screen)

        fun showDateSpinnerAdapter(screenDates: List<ScreenDate>)

        fun showTimeSpinnerAdapter(screenDate: ScreenDate)

        fun showTicket(count: Int)

        fun navigateToSeatSelection(reservationInfo: ReservationInfo)

        fun back()
    }

    interface Presenter : BasePresenter {
        fun loadScreen(id: Int)

        fun createDateSpinnerAdapter(screenDates: List<ScreenDate>)

        fun createTimeSpinnerAdapter(screenDate: ScreenDate)

        fun registerDate(date: LocalDate)

        fun registerTime(time: LocalTime)

        fun updateTicket(count: Int)

        fun plusTicket()

        fun minusTicket()

        fun selectSeat()
    }
}
