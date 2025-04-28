package woowacourse.movie.booking

import android.os.Bundle
import woowacourse.movie.domain.MovieInfo
import woowacourse.movie.domain.TicketCount
import java.time.LocalTime

interface BookingContract {
    interface View {
        fun moveActivity()

        fun setupDateChangeListener()

        fun setupPage()

        fun repairInstanceState(state: Bundle)

        fun confirmButtonHandler()

        fun countButtonHandler()

        fun timeSpinnerSet(times: List<LocalTime>)

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
            movieInfoUIModel: MovieInfo,
            position: Int,
        )
    }
}
