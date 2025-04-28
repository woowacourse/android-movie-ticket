package woowacourse.movie.domain.model.movie

@JvmInline
value class RunningTime(
    val minute: Int,
) {
    init {
        require(minute >= RUNNING_TIME_MIN_MINUTE) {
            INVALID_RUNNING_TIME_MESSAGE.format(minute)
        }
    }

    companion object {
        private const val RUNNING_TIME_MIN_MINUTE = 1
        private const val INVALID_RUNNING_TIME_MESSAGE = "러닝 타임은 ${RUNNING_TIME_MIN_MINUTE}분 이상이어야 합니다. (현재: %d 분)"
    }
}
