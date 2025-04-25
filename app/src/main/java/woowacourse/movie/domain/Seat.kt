package woowacourse.movie.domain

data class Seat(
    val location: String,
    val price: PriceRule,
) {
    val row = location.substring(0, 1)
    val column = location.substring(1).toInt()

    init {
        runCatching {
            require(location.substring(0, 1) in ROWS_RULE) { ERR_OUT_OF_ROW }
            require(location.substring(1).toInt() in COLUMNS_RULE) { ERR_OUT_OF_COLUMN }
        }.onFailure {
            throw IllegalArgumentException(ERR_INVALID_FORMAT)
        }
    }

    companion object {
        private val ROWS_RULE = listOf("A", "B", "C", "D", "E")
        private val COLUMNS_RULE = listOf(1, 2, 3, 4)
        private const val ERR_OUT_OF_ROW = "행 범위를 벗어났습니다."
        private const val ERR_OUT_OF_COLUMN = "열 범위를 벗어났습니다."
        private const val ERR_INVALID_FORMAT = "잘못된 형식입니다"

        fun allCombinations(): List<Seat> =
            buildList {
                ROWS_RULE.forEach { row ->
                    COLUMNS_RULE.forEach { column ->
                        if (row == "A" || row == "B") {
                            add(Seat("$row$column", PriceRule.RANK_B))
                        } else if (row == "C" || row == "D") {
                            add(Seat("$row$column", PriceRule.RANK_S))
                        } else {
                            add(Seat("$row$column", PriceRule.RANK_A))
                        }
                    }
                }
            }
    }
}
