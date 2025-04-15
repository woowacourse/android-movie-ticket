package woowacourse.movie.domain

@JvmInline
value class RunningTime(val time: Int) {
    init {
        require(time > 0) { INVALID_TIME }
    }

    companion object {
        private const val INVALID_TIME = "상영시간은 양수여야 합니다"
    }
}
