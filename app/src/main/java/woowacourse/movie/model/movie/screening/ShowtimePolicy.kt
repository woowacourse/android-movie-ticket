package woowacourse.movie.model.movie.screening

import java.time.LocalDate
import java.time.LocalTime

interface ShowtimePolicy {
    fun showtimes(date: LocalDate): List<LocalTime>
}
