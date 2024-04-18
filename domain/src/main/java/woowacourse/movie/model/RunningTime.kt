package woowacourse.movie.model

import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

@JvmInline
value class RunningTime(val time: Duration) {
    init {
        require(time >= MIN_RUNNING_TIME) {
            "$time - 상영 시간은 $MIN_RUNNING_TIME 이상이어야 합니다."
        }
    }

    companion object {
        private val MIN_RUNNING_TIME = 30.minutes
    }
}
