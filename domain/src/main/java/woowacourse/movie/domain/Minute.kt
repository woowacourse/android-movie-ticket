package woowacourse.movie.domain

@JvmInline
value class Minute(val value: Int) {
    init {
        require(value.isNotNegative()) { NEGATIVE_ERROR }
    }

    private fun Int.isNotNegative(): Boolean = this >= 0

    companion object {
        private const val NEGATIVE_ERROR = "[ERROR] 분은 음수일 수 없습니다."
    }
}
