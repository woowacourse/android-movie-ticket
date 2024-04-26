package woowacourse.movie.presentation.detail

import woowacourse.movie.domain.Movie
import woowacourse.movie.presentation.base.BaseContract
import java.time.LocalDate
import java.time.LocalTime

interface MovieDetailContract {
    interface View : BaseContract.View {
        fun onCountUpdate(count: Int)

        fun onInitView(movie: Movie)

        fun updateDate(dates: List<LocalDate>)

        fun updateTime(times: List<LocalTime>)

        fun onReservationComplete(
            id: Long,
            count: Int,
            localDate: LocalDate,
            localTime: LocalTime,
        )
    }

    interface Presenter {
        fun display(id: Long)

        fun plusReservationCount()

        fun minusReservationCount()

        fun selectDate(
            movie: Movie,
            localDate: LocalDate,
        )

        fun reservation(
            id: Long,
            localDate: LocalDate,
            localTime: LocalTime,
        )
    }
}
