package woowacourse.movie.model

import java.time.LocalDateTime
import java.time.LocalTime

data class ScreeningTime(
    val time: LocalDateTime,
) {
    init {
        require(
            time.toLocalTime() == LocalTime.of(0, 0) ||
            time >= lowerBound() && time <= upperBound()
        ) {
            "시간이 범위를 벗어남"
        }
    }

    private fun lowerBound() =
        LocalDateTime.of(time.toLocalDate(), LocalTime.of(9, 0))

    private fun upperBound() =
        LocalDateTime.of(time.toLocalDate().plusDays(1), LocalTime.of(0, 0))
}
