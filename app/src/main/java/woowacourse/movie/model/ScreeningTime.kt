package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate

class ScreeningTime(private val date: LocalDate) {
    fun schedule(): IntProgression {
        return when (date.dayOfWeek) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> 9..23 step 2
            else -> 10..24 step 2
        }
    }
}
