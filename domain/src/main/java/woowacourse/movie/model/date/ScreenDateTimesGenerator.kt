package woowacourse.movie.model.date

import java.time.LocalDate
import java.time.LocalTime

interface ScreenDateTimesGenerator {
    fun generate(list: List<LocalDate>): ScreenDateTimes
}

object DefaultScreenDateTimesGenerator : ScreenDateTimesGenerator {
    override fun generate(list: List<LocalDate>): ScreenDateTimes {
        val screenDateTimes =
            list.map { date ->
                val times =
                    if (date.isWeekend()) {
                        generateWeekendTimes()
                    } else {
                        generateWeekdayTimes()
                    }
                ScreenDateTime(date, times)
            }
        return ScreenDateTimes(screenDateTimes)
    }

    private fun LocalDate.isWeekend(): Boolean {
        return dayOfWeek.value == 6 || dayOfWeek.value == 7
    }

    private fun generateWeekendTimes(): List<LocalTime> {
        return (9..21 step 2).map { hour -> LocalTime.of(hour, 0) }
    }

    private fun generateWeekdayTimes(): List<LocalTime> {
        return (10..22 step 2).map { hour -> LocalTime.of(hour, 0) }
    }
}
