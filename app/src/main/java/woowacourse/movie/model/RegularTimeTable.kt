package woowacourse.movie.model

import java.time.temporal.ChronoUnit

class RegularTimeTable(
    private val start: ScreeningTime,
    private val end: ScreeningTime,
    private val intervalHours: Int,
) : ScreeningTimeTable {
    override fun getScreeningTimes(): List<ScreeningTime> {
        val minutes = start.time.until(end.time, ChronoUnit.MINUTES)
        val intervalMinutes = intervalHours * 60
        val scheduleNum = (minutes / intervalMinutes).toInt()
        return generateSequence(start.time) {
            it.plusMinutes((intervalHours * 60).toLong())
        }.map { ScreeningTime(it) }.take(scheduleNum + 1).toList()
    }
}
