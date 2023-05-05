package woowacourse.movie.domain.discount

import java.time.LocalTime

class TimeRange(private val startTime: LocalTime, private val endTime: LocalTime) {

    init {
        require(startTime <= endTime) { START_TIME_BIGGER_THAN_END_TIME_ERROR }
    }

    operator fun contains(time: LocalTime): Boolean = time in startTime..endTime

    companion object {
        private const val START_TIME_BIGGER_THAN_END_TIME_ERROR = "시작 시간은 마지막 시간보다 빠르거나 같아야 합니다."
    }
}
