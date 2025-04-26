package woowacourse.movie.detailbooking

import android.os.Bundle
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalTime

interface DetailBookingContract {
    interface Presenter {
        fun set(movie: Movie)
        fun clickedDate(position: Int)
        fun clickedTime(position: Int)
        fun increasedCount()
        fun decreasedCount()
        fun clickedButton()
        fun saveState(outState: Bundle)
        fun restoreState(savedInstanceState: Bundle)
    }
    interface View {
        fun showMovieData(movie: Movie)
        fun showMovieSchedule(movieSchedule: List<LocalDate>, selectedDatePosition: Int)
        fun showMovieScreeningTime(screeningTime: List<LocalTime>, selectedTimePosition: Int)
        fun showCount(count: Int)
        fun showDialog(ticket: Ticket)
    }
}