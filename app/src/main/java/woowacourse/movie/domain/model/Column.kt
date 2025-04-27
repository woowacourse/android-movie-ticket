package woowacourse.movie.domain.model

@JvmInline
value class Column(val value: Int) {
    init {
        require(value >= 0) { INVALID_VALUE_RANGE }
    }

    companion object {
        const val INVALID_VALUE_RANGE = "열의 숫자는 0과 같거나 커야 합니다"
    }
}
