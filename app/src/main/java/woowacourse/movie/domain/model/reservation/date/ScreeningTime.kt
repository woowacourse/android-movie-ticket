package woowacourse.movie.domain.model.reservation.date

import java.time.LocalTime

class ScreeningTime(
    val runningTime: Int,
    isWeekend: Boolean,
) {
    var startTime: LocalTime = LocalTime.of(DEFAULT_HOUR, DEFAULT_MINUTE)
        private set

    init {
        initStartTime(isWeekend)
    }

    fun initStartTime(isWeekend: Boolean) {
        startTime =
            when (isWeekend) {
                true -> LocalTime.of(DEFAULT_HOUR, DEFAULT_MINUTE)
                false -> LocalTime.of(DEFAULT_WEEKDAY_HOUR, DEFAULT_MINUTE)
            }
    }

    fun changeStartTime(
        hour: Int,
        minute: Int,
        isWeekend: Boolean,
    ) {
        if (isInScreeningTimeRange(hour, minute, isWeekend)) {
            startTime = LocalTime.of(hour, minute)
        }
    }

    private fun isInScreeningTimeRange(
        hour: Int,
        minute: Int,
        isWeekend: Boolean,
    ): Boolean {
        val time = LocalTime.of(hour, minute)
        return isAfterDefaultStartTime(time, isWeekend) && isBeforeMidnightTime(time)
    }

    private fun isAfterDefaultStartTime(
        time: LocalTime,
        isWeekend: Boolean,
    ): Boolean {
        return when (isWeekend) {
            true ->
                LocalTime.of(DEFAULT_HOUR, DEFAULT_MINUTE) == time ||
                    LocalTime.of(DEFAULT_HOUR, DEFAULT_MINUTE).isBefore(time)

            false ->
                LocalTime.of(DEFAULT_WEEKDAY_HOUR, DEFAULT_MINUTE) == time ||
                    LocalTime.of(DEFAULT_WEEKDAY_HOUR, DEFAULT_MINUTE).isBefore(time)
        }
    }

    private fun isBeforeMidnightTime(time: LocalTime): Boolean {
        return time.isBefore(LocalTime.of(LAST_HOUR_OF_DAY, LAST_MINUTE))
    }

    fun getEndTime(): LocalTime {
        return startTime.plusMinutes(runningTime.toLong())
    }

    companion object {
        const val DEFAULT_WEEKDAY_HOUR = 10
        const val DEFAULT_HOUR = 9
        const val DEFAULT_MINUTE = 0
        const val LAST_HOUR_OF_DAY = 23
        const val LAST_MINUTE = 59
    }
}
