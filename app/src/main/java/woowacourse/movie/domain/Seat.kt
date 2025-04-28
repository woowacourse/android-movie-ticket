package woowacourse.movie.domain

import woowacourse.movie.domain.rules.PriceRule

data class Seat(
    val location: String,
    val price: PriceRule,
) {
    val row = location.substring(0, 1)
    val column = location.substring(1).toInt()

    init {
        runCatching {
            require(location.substring(0, 1) in ROWS) { ERR_OUT_OF_ROW }
            require(location.substring(1).toInt() in COLUMNS) { ERR_OUT_OF_COLUMN }
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
