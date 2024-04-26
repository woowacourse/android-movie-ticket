package woowacourse.movie.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

class MovieDate(
    movieDate: LocalDate = LocalDate.now(),
    movieTime: LocalDateTime = LocalDateTime.now(),
) {
    var currentDate: LocalDate = movieDate
        private set
    var currentTime: LocalDateTime = movieTime
        private set

    fun setCurrentDate(newDate: LocalDate) {
        currentDate = newDate
    }

    fun setCurrentTime(newTime: LocalDateTime) {
        currentTime = newTime
    }
}
