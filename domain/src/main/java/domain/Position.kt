package domain

data class Position(val row: Int, val col: Int) {

    init {
        require(row in MIN_ROW_COL..MAX_ROW) { }
        require(col in MIN_ROW_COL..MAX_COL) { }
    }

    companion object {

        private const val MIN_ROW_COL = 1
        private const val MAX_ROW = 5
        private const val MAX_COL = 4
        fun of(position: Int): Position =
            Position((position / MAX_COL) + MIN_ROW_COL, (position % MAX_COL) + MIN_ROW_COL)
    }
}
