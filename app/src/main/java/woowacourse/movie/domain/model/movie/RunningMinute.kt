package woowacourse.movie.domain.model.movie

@JvmInline
value class RunningMinute(val value: Int) {
    init {
        require(value > 0) { INVALID_MINUTE }
    }

    companion object {
        private const val INVALID_MINUTE = "상영시간은 양수여야 합니다"
    }
}
