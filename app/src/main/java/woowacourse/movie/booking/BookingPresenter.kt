package woowacourse.movie.booking

import android.os.Bundle
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.dto.MovieInfo
import woowacourse.movie.util.MovieScheduleUtils

class BookingPresenter : BookingContract.Presenter {
    override fun onCreateView(
        view: BookingContract.View,
        savedInstanceState: Bundle?,
    ) {
        view.setupPage()
        view.setupDateChangeListener()
        view.countButtonHandler()
        view.confirmButtonHandler()

        if (savedInstanceState != null) {
            view.repairInstanceState(savedInstanceState)
        }
    }

    override fun onBookButtonClick(
        view: BookingContract.View,
        count: TicketCount,
    ) {
        if (count.count == 0) return
        view.askToConfirmBook()
    }

    override fun onYesClick(view: BookingContract.View) {
        view.moveActivity()
    }

    override fun onUpButtonClick(
        view: BookingContract.View,
        ticketCount: TicketCount,
    ) {
        ticketCount.upCount()
        view.changeTicketCount(ticketCount)
    }

    override fun onDownButtonClick(
        view: BookingContract.View,
        ticketCount: TicketCount,
    ) {
        ticketCount.downCount()
        view.changeTicketCount(ticketCount)
    }

    override fun dateSpinnerSelect(
        view: BookingContract.View,
        movieInfo: MovieInfo,
        position: Int,
    ) {
        val selectedDate =
            MovieScheduleUtils
                .generateScreeningDates(movieInfo.startDate, movieInfo.endDate)
                .getOrNull(position)
        selectedDate?.let {
            val selectedTimes = MovieScheduleUtils.generateScreeningTimesFor(it)
            view.timeSpinnerSet(selectedTimes)
        }
    }
}
