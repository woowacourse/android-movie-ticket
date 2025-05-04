package woowacourse.movie.domain

@JvmInline
value class Row (val value: Int) {
    init {
        require(value in MIN_ROW..MAX_ROW) { "허용되지 않은 좌석의 행입니다." }
    }

    companion object {
        private const val MIN_ROW = 1
        private const val MAX_ROW = 4
    }
}