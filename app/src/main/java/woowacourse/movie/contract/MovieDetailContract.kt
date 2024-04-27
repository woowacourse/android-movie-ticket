package woowacourse.movie.contract

import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningPeriod
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.schedule.ScreeningTime


interface MovieDetailContract {
    interface View {
        fun displayMovie(movieBrief: Movie)

        fun displayScreeningDates(dates: List<ScreeningDate>)

        fun displayScreeningTimes(times: List<ScreeningTime>)

        fun displayTicketNum(ticketNum: Int)

        fun navigateToPurchaseConfirmation()

    }

    interface Presenter {
        fun loadMovie(movieId: Int)

        fun loadScreeningPeriod(period: ScreeningPeriod)

        fun selectScreeningDate(date: ScreeningDate)

        fun plusTicketNum()

        fun minusTicketNum()

        fun purchase(
            screeningId: Int,
        )
    }
}
