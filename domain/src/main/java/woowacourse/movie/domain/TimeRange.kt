package woowacourse.movie.domain

import java.time.LocalTime

class TimeRange(private val startTime: LocalTime, private val endTime: LocalTime) {

    operator fun contains(time: LocalTime): Boolean = time in startTime..endTime
}
