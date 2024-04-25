package woowacourse.movie.presentation.screen.detail

import android.content.Intent
import java.time.LocalDate
import java.time.LocalTime

interface MovieDetailContract {
    interface View {
        fun setUpView(
            img: Int,
            title: String,
            screenDate: String,
            runningTime: Int,
            description: String,
        )

        fun setUpSpinner(
            dates: List<LocalDate>,
            times: List<LocalTime>,
        )

        fun updateTicketCount(count: Int)

        fun updateTimeSpinner(times: List<LocalTime>)
    }

    interface Presenter {
        fun fetchScreenInfo(movieId: Int)

        fun subTicketCount()

        fun addTicketCount()

        fun onClickedSelectSeatButton(intent: Intent)

        fun ticketCount(): Int

        fun restoreTicketCount(count: Int)

        fun onSelectedDateTime(date: LocalDate)

        fun registerScreenDate(date: LocalDate)

        fun registerScreenTime(time: LocalTime)
    }
}
