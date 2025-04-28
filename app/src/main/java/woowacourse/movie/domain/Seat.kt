package woowacourse.movie.domain

import woowacourse.movie.domain.rules.PriceRule

data class Seat(
    val row: String,
    val column: Int,
    val price: PriceRule,
) {
    init {
        runCatching {
            require(row in ROWS) { ERR_OUT_OF_ROW }
            require(column in COLUMNS) { ERR_OUT_OF_COLUMN }
        }.onFailure {
            throw IllegalArgumentException(ERR_INVALID_FORMAT)
        }
    }

    companion object {
        private const val ERR_OUT_OF_ROW = "행 범위를 벗어났습니다."
        private const val ERR_OUT_OF_COLUMN = "열 범위를 벗어났습니다."
        private const val ERR_INVALID_FORMAT = "잘못된 형식입니다"
        val ROWS = listOf("A", "B", "C", "D", "E")
        val COLUMNS = listOf(1, 2, 3, 4)
    }
}
