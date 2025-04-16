package woowacourse.movie.domain

import java.time.LocalDate

class MovieSchedule(
    private val date: Date,
) {
    fun dateSpinner(currentDate: LocalDate): List<LocalDate> {
        val date =
            generateSequence(date.startDate) {
                if (it < date.endDate) it.plusDays(1) else null
            }.toList()

        return date.filter { it >= currentDate }
    }
}
