package woowacourse.movie.booking

import android.os.Bundle
import woowacourse.movie.model.MovieInfo
import woowacourse.movie.model.TicketCount
import woowacourse.movie.uiModel.TicketUIModel
import java.time.LocalTime

interface BookingContract {
    interface View {
        fun moveActivity(ticketUIModel: TicketUIModel)

        fun setupDateChangeListener()

        fun setupPage()

        fun repairInstanceState(state: Bundle)

        fun confirmButtonHandler()

        fun countButtonHandler()

        fun timeSpinnerSet(times: List<LocalTime>)

        fun changeTicketCount(ticketCountValue: TicketCount)
    }

    interface Presenter {
        fun onCreateView(savedInstanceState: Bundle?)

        fun onBookButtonClick(
            title: String,
            date: String,
            time: String,
            count: TicketCount,
        )

        fun onUpButtonClick(ticketCount: TicketCount)

        fun onDownButtonClick(ticketCount: TicketCount)

        fun dateSpinnerSelect(
            movieInfoUIModel: MovieInfo,
            position: Int,
        )
    }
}
