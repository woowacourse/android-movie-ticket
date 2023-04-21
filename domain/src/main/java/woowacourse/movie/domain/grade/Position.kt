package woowacourse.movie.domain.grade

class Position private constructor(val rowIndex: Int, val columnIndex: Int) {
    init {
        validateCoordinate(columnIndex)
    }

    private fun validateCoordinate(columnIndex: Int) {
        require(rowIndex in START_INDEX..MAXIMUM_ROW_INDEX) { OVER_MAXIMUM_INDEX }
        require(columnIndex in START_INDEX..MAXIMUM_COLUMN_INDEX) { OVER_MAXIMUM_INDEX }
    }

    companion object {
        private const val START_INDEX = 0
        private const val MAXIMUM_ROW_INDEX = 4
        private const val MAXIMUM_COLUMN_INDEX = 3
        private const val OVER_MAXIMUM_INDEX = "좌석의 최대 범위를 벗어났습니다."
        private const val POSITION_CACHE_NULL_ERROR = "Position이 캐싱된 값이 없습니다"

        private fun getIndexRange(maximum: Int): IntRange = START_INDEX..maximum

        private val COORDINATE: Map<Pair<Int, Int>, Position> =
            getIndexRange(MAXIMUM_ROW_INDEX).map { row -> getIndexRange(MAXIMUM_COLUMN_INDEX).map { column -> row to column } }
                .flatten()
                .associateWith { Position(it.first, it.second) }

        fun from(row: Int, column: Int): Position =
            COORDINATE[row to column] ?: throw IllegalStateException(POSITION_CACHE_NULL_ERROR)
    }
}
