package woowacourse.movie.detailbooking

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieSchedule
import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.domain.ScreeningTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DetailBookingPresenter(private val view: DetailBookingContract.View) : DetailBookingContract.Presenter {
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
        val reservationInfo = ReservationInfo(movie.title, LocalDateTime.of(selectedDate, selectedTime), personnel)
        view.showNextActivity(reservationInfo)
    }

    override fun restoreState(personnel: Int, selectedDatePosition: Int, selectedTimePosition: Int) {
        view.showMovieSchedule(movieSchedule, selectedDatePosition)
        screeningTime = ScreeningTime(movieSchedule[selectedDatePosition].atStartOfDay()).selectableTimes()
        view.showMovieScreeningTime(screeningTime, selectedTimePosition)
        view.showCount(personnel)
    }

    companion object {
        const val DEFAULT_PERSONNEL = 1
    }
}
