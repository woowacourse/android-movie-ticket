package woowacourse.movie.domain

import java.time.LocalDate

class MovieSchedule(
    private val date: Date,
) {
    fun dateSpinner(currentDate: LocalDate): List<LocalDate> {
        val date =
            generateSequence(date.startDate) {
                if (it < date.endDate) it.plusDays(DATE_INTERVAL) else null
            }.toList()

        return date.filter { it >= currentDate }
    }

    companion object {
        private const val DATE_INTERVAL = 1L
    }
}
