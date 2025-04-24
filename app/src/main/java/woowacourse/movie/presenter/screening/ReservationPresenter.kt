package woowacourse.movie.presenter.screening

import woowacourse.movie.contract.ReservationContract
import woowacourse.movie.domain.screening.Screening

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val screening: Screening,
) : ReservationContract.Presenter {
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
}
