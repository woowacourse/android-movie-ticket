package woowacourse.movie.model

import android.provider.CalendarContract.Instances.END
import java.time.LocalTime

data class ScreeningTime(
    val time: LocalTime
) {
    init {
        require(time >= START_TIME && time <= END_TIME) {
            "시간이 범위를 벗어남"
        }
    }

    companion object {
        private val START_TIME = LocalTime.of(9, 0)
        private val END_TIME = LocalTime.of(24, 0)
    }
}
