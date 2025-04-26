package woowacourse.movie.detailbooking

import android.os.Bundle
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieSchedule
import woowacourse.movie.domain.ScreeningTime
import woowacourse.movie.domain.Ticket
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DetailBookingPresenter(private val view: DetailBookingContract.View): DetailBookingContract.Presenter {
    private lateinit var movie: Movie
    private lateinit var movieSchedule: List<LocalDate>
    private lateinit var screeningTime: List<LocalTime>

    private var selectedDatePosition = 0
    private var selectedTimePosition = 0
    private var personnel = DEFAULT_PERSONNEL

    override fun set(movie: Movie) {
        this.movie = movie

        this.movieSchedule = MovieSchedule(movie.date).selectableDates(LocalDate.now())
        this.screeningTime = ScreeningTime(movieSchedule[selectedDatePosition].atStartOfDay()).selectableTimes()
        if (screeningTime.isEmpty()) {
            this.movieSchedule = MovieSchedule(movie.date).selectableDates(LocalDate.now().plusDays(1))
            this.screeningTime = ScreeningTime(movieSchedule[selectedDatePosition].atStartOfDay().plusDays(1)).selectableTimes()
        }


        view.showMovieData(movie)
        view.showMovieSchedule(movieSchedule, selectedDatePosition)
        view.showMovieScreeningTime(screeningTime, selectedTimePosition)
        view.showCount(personnel)
    }

    override fun clickedDate(position: Int) {
        selectedDatePosition = position
        screeningTime = ScreeningTime(movieSchedule[position].atStartOfDay()).selectableTimes()
        selectedTimePosition = 0
        view.showMovieScreeningTime(screeningTime, selectedTimePosition)
    }

    override fun clickedTime(position: Int) {
        selectedTimePosition = position
    }

    override fun increasedCount() {
        personnel += 1
        view.showCount(personnel)
    }

    override fun decreasedCount() {
        personnel = (personnel - 1).coerceAtLeast(DEFAULT_PERSONNEL)
        view.showCount(personnel)
    }

    override fun clickedButton() {
        val selectedDate = movieSchedule[selectedDatePosition]
        val selectedTime = screeningTime[selectedTimePosition]
        val ticket = Ticket(movie.title, LocalDateTime.of(selectedDate, selectedTime), personnel)
        view.showDialog(ticket)
    }

    override fun saveState(outState: Bundle) {
        outState.putInt(KEY_PERSONNEL_COUNT, personnel)
        outState.putInt(KEY_DATE_POSITION, selectedDatePosition)
        outState.putInt(KEY_TIME_POSITION, selectedTimePosition)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        personnel = savedInstanceState.getInt(KEY_PERSONNEL_COUNT, DEFAULT_PERSONNEL)
        selectedDatePosition = savedInstanceState.getInt(KEY_DATE_POSITION, 0)
        selectedTimePosition = savedInstanceState.getInt(KEY_TIME_POSITION, 0)

        view.showMovieSchedule(movieSchedule, selectedDatePosition)
        screeningTime = ScreeningTime(movieSchedule[selectedDatePosition].atStartOfDay()).selectableTimes()
        view.showMovieScreeningTime(screeningTime, selectedTimePosition)
        view.showCount(personnel)
    }

    companion object {
        private const val DEFAULT_PERSONNEL = 1
        private const val KEY_PERSONNEL_COUNT = "personnel_count"
        private const val KEY_DATE_POSITION = "movieDate_position"
        private const val KEY_TIME_POSITION = "timeTable_position"
    }
}