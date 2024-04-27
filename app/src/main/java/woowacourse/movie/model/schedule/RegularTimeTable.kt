package woowacourse.movie.model.schedule

import java.time.temporal.ChronoUnit

class RegularTimeTable(
    private val start: ScreeningDateTime,
    private val end: ScreeningDateTime,
    private val intervalHours: Int,
) : ScreeningTimeTable {
    override fun getScreeningTimes(): List<ScreeningDateTime> {
        val minutes = start.dateTime.until(end.dateTime, ChronoUnit.MINUTES)
        val intervalMinutes = intervalHours * 60
        val scheduleNum = (minutes / intervalMinutes).toInt()
        return generateSequence(start.dateTime) {
            it.plusMinutes((intervalHours * 60).toLong())
        }.map { ScreeningDateTime(it) }.take(scheduleNum + 1).toList()
    }
}
