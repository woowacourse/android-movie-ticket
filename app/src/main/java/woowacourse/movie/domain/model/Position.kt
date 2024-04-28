package woowacourse.movie.domain.model

data class Position(val row: Int, val col: Int) {
    init {
        require(row in MIN_ROW_INDEX..MAX_ROW_INDEX) { "row: $MIN_ROW_INDEX ~ $MAX_ROW_INDEX" }
        require(col in MIN_COL_INDEX..MAX_COL_INDEX) { "col: $MIN_COL_INDEX ~ $MAX_COL_INDEX" }
    }

    companion object {
        private const val MIN_ROW_INDEX = 0
        private const val MAX_ROW_INDEX = 4

        private const val MIN_COL_INDEX = 0
        private const val MAX_COL_INDEX = 3
    }
}
