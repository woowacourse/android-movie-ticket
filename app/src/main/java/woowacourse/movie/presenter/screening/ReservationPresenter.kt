package woowacourse.movie.presenter.screening

import woowacourse.movie.contract.ReservationContract
import woowacourse.movie.domain.screening.Screening
import java.time.LocalDate

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val screening: Screening,
) : ReservationContract.Presenter {
    override fun presentPoster() {
        view.setPoster(screening.id)
    }

    override fun presentTitle() {
        view.setTitle(screening.title)
    }

    override fun presentPeriod() {
        screening.run {
            view.setPeriod(
                startYear,
                startMonth,
                startDay,
                endYear,
                endMonth,
                endDay,
            )
        }
    }

    override fun presentRunningTime() {
        view.setRunningTime(screening.runningTime)
    }

    override fun presentDates() {
        view.setDates(screening.availableDates())
    }

    override fun presentTimes(date: LocalDate) {
        view.setTimes(screening.showtimes(date))
    }

    override fun plusTicketCount(currentCount: Int) {
        view.setTicketCount(currentCount.plus(1))
    }

    override fun minusTicketCount(currentCount: Int) {
        view.setTicketCount(currentCount.minus(1).coerceAtLeast(1))
    }

    override fun tryReservation() {
        view.showConfirmDialog()
    }

    override fun confirmReservation() {
        view.navigateToTicketScreen(screening.title)
    }
}
