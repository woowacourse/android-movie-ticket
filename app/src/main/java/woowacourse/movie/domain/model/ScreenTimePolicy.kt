package woowacourse.movie.domain.model

import java.time.LocalDate
import java.time.LocalTime

interface ScreenTimePolicy {
    fun screeningTimes(date: LocalDate): List<LocalTime>
}
