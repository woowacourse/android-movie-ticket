package woowacourse.movie.model.board

data class BoardSize(val width: Int, val height: Int) {

    init {
        require(width >= MIN_BOARD_SIZE_RANGE && height >= MIN_BOARD_SIZE_RANGE) {
            "width=$width, height=$height : 좌석은 $MIN_BOARD_SIZE_RANGE 사이에 위치해야 합니다."
        }
    }

    fun isInBounds(position: Position): Boolean {
        val (x, y) = position
        return x <  height && y < width
    }

    companion object {
        private const val MIN_BOARD_SIZE_RANGE: Int = 3
    }
}