package woowacourse.movie.booking

import android.os.Bundle
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.uiModel.MovieInfo

interface BookingContract {
    interface View {
        fun moveActivity()

        fun setupDateChangeListener()

        fun setupPage()

        fun repairInstanceState(state: Bundle)

        fun confirmButtonHandler()

        fun countButtonHandler()

        fun askToConfirmBook()

        fun timeSpinnerSet(times: List<String>)

        fun changeTicketCount(ticketCountValue: TicketCount)
    }

    interface Presenter {
        fun onCreateView(
            view: View,
            savedInstanceState: Bundle?,
        )

        fun onBookButtonClick(
            view: View,
            count: TicketCount,
        )

        fun onUpButtonClick(
            view: View,
            ticketCount: TicketCount,
        )

        fun onDownButtonClick(
            view: View,
            ticketCount: TicketCount,
        )

        fun dateSpinnerSelect(
            view: View,
            movieInfo: MovieInfo,
            position: Int,
        )

        fun onYesClick(view: View)
    }
}
