package woowacourse.movie.contract

import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningPeriod
import woowacourse.movie.model.movie.Movie


interface MovieDetailContract {
    interface View {
        fun displayMovie(movieBrief: Movie)

        fun displayScreeningDates(period: ScreeningPeriod)

        fun displayScreeningTimes(date: ScreeningDate)

        fun displayTicketNum(ticketNum: Int)

        fun navigateToPurchaseConfirmation()

    }

    interface Presenter {
        fun loadMovie(movieId: Int)

        fun loadScreeningPeriod(period: ScreeningPeriod)

        fun selectScreeningDate(dateIndex: Int)

        fun plusTicketNum()

        fun minusTicketNum()

        fun purchase(
            screeningId: Int,
        )
    }
}
