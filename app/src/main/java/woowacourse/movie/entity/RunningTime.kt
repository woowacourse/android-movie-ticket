package woowacourse.movie.entity

@JvmInline
value class RunningTime(val value: Int) : java.io.Serializable {
    init {
        require(value > 0) { RUNNING_TIME_ERROR_MESSAGE }
    }

    companion object {
        private const val RUNNING_TIME_ERROR_MESSAGE = "[ERROR] 상영 시간은 1분 이상이여야 합니다."
    }
}
