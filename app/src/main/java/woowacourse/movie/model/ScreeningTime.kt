package woowacourse.movie.model

import java.time.LocalTime

data class ScreeningTime(val time: LocalTime) {
    companion object {
        private const val MAX_HOUR = 24
        private val HOUR_RANGE = 0..23
        private val MINUTE_RANGE = 0..59

        fun of(hourOfDay: Int, minuteOfHour: Int = 0): ScreeningTime {
            val hour = if (hourOfDay == MAX_HOUR) 0 else hourOfDay
            require(hour in HOUR_RANGE) { " " }
            require(minuteOfHour in MINUTE_RANGE) { " " }

            return ScreeningTime(LocalTime.of(hour, minuteOfHour))
        }
    }
}
