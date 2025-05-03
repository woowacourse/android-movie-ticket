// BookingPresenter.kt
package woowacourse.movie.booking

import android.os.Bundle
import woowacourse.movie.model.MovieScheduleGenerator
import woowacourse.movie.model.TicketCount
import woowacourse.movie.uiModel.TicketUIModel
import java.time.LocalDate

class BookingPresenter(
    private val view: BookingContract.View,
) : BookingContract.Presenter {
    override fun onCreateView(savedInstanceState: Bundle?) {
        view.setupDateChangeListener()
        view.confirmButtonHandler()
        view.countButtonHandler()
    }

    override fun onBookButtonClick(
        title: String,
        date: String,
        time: String,
        count: TicketCount,
    ) {
        if (count.count == 0) return
        val ticket =
            TicketUIModel(
                title = title,
                date = date,
                time = time,
                seats = emptyList(),
                count = count.count,
                money = 0,
            )
        view.navigateToResult(ticket)
    }

    override fun upTicketCount(ticketCount: TicketCount) {
        ticketCount.upCount()
        view.changeTicketCount(ticketCount)
    }

    override fun downTicketCount(ticketCount: TicketCount) {
        ticketCount.downCount()
        view.changeTicketCount(ticketCount)
    }

    override fun changeTimesByDate(selectedDate: LocalDate) {
        val times = MovieScheduleGenerator.generateScreeningTimesFor(selectedDate)
        view.showAvailableTime(selectedDate)
    }
}
