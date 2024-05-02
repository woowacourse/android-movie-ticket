package woowacourse.movie.model.board

data class BoardSize(val width: Int, val height: Int) {
    init {
        require(width >= MIN_BOARD_SIZE && height >= MIN_BOARD_SIZE) {
            "width=$width, height=$height : 좌석은 $MIN_BOARD_SIZE 이상이어야 합니다."
        }
    }

    fun isInBounds(position: Position): Boolean {
        val (x, y) = position
        return x < height && y < width
    }

    companion object {
        private const val MIN_BOARD_SIZE: Int = 3
    }
}
