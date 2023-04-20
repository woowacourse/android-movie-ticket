package com.woowacourse.movie.domain.seat

data class SeatPosition(val row: Row, val col: Col) {

    init {
        require(row.x in ROW_MIN_RANGE until ROW_MAX_RANGE) { ERROR_ROW_VALUE_IS_OVER }
        require(col.y in COL_MIN_RANGE until COL_MAX_RANGE) { ERROR_COL_VALUE_IS_OVER }
    }

    companion object {
        private const val ROW_MIN_RANGE = 0
        private const val ROW_MAX_RANGE = 5
        private const val COL_MIN_RANGE = 0
        private const val COL_MAX_RANGE = 4

        private const val ERROR_ROW_VALUE_IS_OVER = "행의 범위를 벗어났습니다"
        private const val ERROR_COL_VALUE_IS_OVER = "열의 범위를 벗어났습니다"
    }
}
