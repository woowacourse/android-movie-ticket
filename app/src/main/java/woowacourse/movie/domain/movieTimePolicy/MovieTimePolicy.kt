package woowacourse.movie.domain.movieTimePolicy

import java.time.LocalDate
import java.time.LocalTime

interface MovieTimePolicy {
    fun generateTime(date: LocalDate): List<LocalTime>
}
