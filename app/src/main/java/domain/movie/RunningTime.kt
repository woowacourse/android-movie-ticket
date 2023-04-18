package domain.movie

@JvmInline
value class RunningTime(val value: Int) {

    init {
        require(value >= MINIMUM_MINUTE) {
            RUNNING_TIME_ERROR
        }
    }

    companion object {
        private const val RUNNING_TIME_ERROR = "[ERROR] 러닝타임은 1분 이상이다."
        private const val MINIMUM_MINUTE = 1
    }
}
