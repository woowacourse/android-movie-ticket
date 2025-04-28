package woowacourse.movie.booking

import android.os.Bundle
import woowacourse.movie.model.MovieInfo
import woowacourse.movie.model.MovieScheduleGenerator
import woowacourse.movie.model.TicketCount
import woowacourse.movie.uiModel.TicketUIModel

class BookingPresenter(
    val view: BookingContract.View,
) : BookingContract.Presenter {
    override fun onCreateView(savedInstanceState: Bundle?) {
        view.setupPage()
        view.setupDateChangeListener()
        view.countButtonHandler()
        view.confirmButtonHandler()

        if (savedInstanceState != null) {
            view.repairInstanceState(savedInstanceState)
        }
    }

    override fun onBookButtonClick(
        title: String,
        date: String,
        time: String,
        count: TicketCount,
    ) {
        if (count.count == 0) return
        val ticketUIModel =
            TicketUIModel(
                title = title,
                date = date,
                time = time,
                count = count.count,
                money = 0,
            )
        view.moveActivity(ticketUIModel)
    }

    override fun onUpButtonClick(ticketCount: TicketCount) {
        ticketCount.upCount()
        view.changeTicketCount(ticketCount)
    }

    override fun onDownButtonClick(ticketCount: TicketCount) {
        ticketCount.downCount()
        view.changeTicketCount(ticketCount)
    }

    override fun dateSpinnerSelect(
        movieInfo: MovieInfo,
        position: Int,
    ) {
        val selectedDate =
            MovieScheduleGenerator
                .generateScreeningDates(movieInfo.startDate, movieInfo.endDate)
                .getOrNull(position)
        selectedDate?.let {
            val selectedTimes = MovieScheduleGenerator.generateScreeningTimesFor(it)
            view.timeSpinnerSet(selectedTimes)
        }
    }
}
