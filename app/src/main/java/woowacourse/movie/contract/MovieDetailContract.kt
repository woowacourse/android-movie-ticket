package woowacourse.movie.contract

import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.model.schedule.ScreeningPeriod
import woowacourse.movie.uiModels.movie.MovieDetail
import java.time.LocalDateTime

interface MovieDetailContract {
    interface View {
        fun displayMovie(movieDetail: MovieDetail)

        fun displayScreeningDates(dates: List<ScreeningDate>)

        fun displayScreeningTimes(times: List<ScreeningDateTime>)

        fun displayTicketNum(ticketNum: Int)

        fun navigateToSeatSelection(
            movieId: Int,
            ticketNum: Int,
            reservedDateTime: LocalDateTime,
        )
    }

    interface Presenter {
        fun loadMovie(movieId: Int)

        fun loadScreeningPeriod(period: ScreeningPeriod)

        fun selectScreeningDate(date: ScreeningDate)

        fun selectScreeningTime(time: ScreeningDateTime)

        fun plusTicketNum()

        fun minusTicketNum()

        fun confirm()
    }
}
