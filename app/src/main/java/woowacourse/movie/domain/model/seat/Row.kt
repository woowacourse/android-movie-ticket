package woowacourse.movie.domain.model.seat

@JvmInline
value class Row(
    val value: Char,
) {
    init {
        require(value in 'A'..'Z') { ERROR_INVALID_ROW_VALUE }
    }

    companion object {
        private const val ERROR_INVALID_ROW_VALUE = "올바르지 않은 열 정보입니다."
    }
}
