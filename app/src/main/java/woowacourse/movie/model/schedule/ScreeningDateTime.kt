package woowacourse.movie.model.schedule

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class ScreeningDateTime (
    val dateTime: LocalDateTime,
) {
    init {
        require(
            dateTime.toLocalTime() == LocalTime.of(0, 0) ||
                    dateTime >= lowerBound() && dateTime <= upperBound()
        ) {
            "시간이 범위를 벗어남"
        }
    }

    private fun lowerBound() =
        LocalDateTime.of(dateTime.toLocalDate(), LocalTime.of(9, 0))

    private fun upperBound() =
        LocalDateTime.of(dateTime.toLocalDate().plusDays(1), LocalTime.of(0, 0))

    companion object {
        fun of(date: LocalDate, hour: Int, min: Int): ScreeningDateTime {
            val additionalDays = (hour / 24).toLong()
            val overflowedDate = date.plusDays(additionalDays)
            val time = LocalTime.of(hour % 24, min)
            val dateTime = LocalDateTime.of(overflowedDate, time)
            return ScreeningDateTime(dateTime)
        }
    }
}
