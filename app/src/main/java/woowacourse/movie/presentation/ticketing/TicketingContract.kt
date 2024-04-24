package woowacourse.movie.presentation.ticketing

import android.widget.AdapterView.OnItemSelectedListener
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.LocalTime

interface TicketingContract {
    interface View {
        fun assignInitialView(
            movie: Movie,
            count: Int,
        )

        fun setUpDateSpinners(
            screeningDates: List<LocalDate>,
            listener: OnItemSelectedListener,
        )

        fun setUpTimeSpinners(screeningTimes: List<LocalTime>)

        fun updateCount(count: Int)

        fun navigate(
            movieId: Int,
            count: Int,
        )

        fun showErrorMessage(message: String?)
    }

    interface Presenter {
        fun assignInitialView()

        fun decreaseCount()

        fun increaseCount()

        fun navigate()
    }
}
