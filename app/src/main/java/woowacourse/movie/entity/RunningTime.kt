package woowacourse.movie.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class RunningTime(val value: Int) : Parcelable {
    init {
        require(value > MIN_RUNNING_TIME_RANGE) { RUNNING_TIME_ERROR_MESSAGE }
    }

    companion object {
        private const val RUNNING_TIME_ERROR_MESSAGE = "[ERROR] 상영 시간은 1분 이상이여야 합니다."
        private const val MIN_RUNNING_TIME_RANGE = 0
    }
}
