package woowacourse.movie.domain.model

@JvmInline
value class RunningTime(
    val minute: Int,
) {
    init {
        require(minute >= MINIMUM_RUNNING_TIME) { INVALID_RUNNING_TIME_MESSAGE }
    }

    companion object {
        private const val MINIMUM_RUNNING_TIME = 1
        private const val INVALID_RUNNING_TIME_MESSAGE = "최소 상영시간은 ${MINIMUM_RUNNING_TIME}분 이상 이어야 합니다"
    }
}
