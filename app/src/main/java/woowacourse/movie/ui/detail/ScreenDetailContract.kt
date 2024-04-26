package woowacourse.movie.ui.detail

import woowacourse.movie.domain.model.DateRange
import woowacourse.movie.ui.ScreenDetailUI
import java.time.LocalDate

interface ScreenDetailContract {
    interface View {
        fun showScreen(screen: ScreenDetailUI)

        fun showTicket(count: Int)

        fun showDatePicker(dateRange: DateRange)

        fun showTimePicker(date: LocalDate)

        fun showDateWithPosition(datePosition: Int)

        fun showTimeWithPosition(timePosition: Int)

        fun navigateToReservation(navigationId: Int)

        fun showToastMessage(e: Throwable)

        fun showSnackBar(e: Throwable)

        fun goToBack(e: Throwable)

        fun unexpectedFinish(e: Throwable)
    }

    interface Presenter {
        fun loadScreen(screenId: Int)

        fun loadTicket()

        fun saveDatePosition(datePosition: Int)

        fun loadDatePosition()

        fun saveTimePosition(timePosition: Int)

        fun loadTimePosition()

        fun saveTicket(count: Int)

        fun plusTicket()

        fun minusTicket()

        fun reserve(screenId: Int)
    }
}
