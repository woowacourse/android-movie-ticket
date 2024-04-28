package woowacourse.movie.domain.model

import java.time.LocalDate
import java.time.LocalTime

class MovieDateTime(
    movieDate: LocalDate = LocalDate.now(),
    movieTime: LocalTime = LocalTime.now(),
) {
    var currentDate: LocalDate = movieDate
        private set
    var currentTime: LocalTime = movieTime
        private set

    fun setCurrentDate(newDate: LocalDate) {
        currentDate = newDate
    }

    fun setCurrentTime(newTime: LocalTime) {
        currentTime = newTime
    }
}
