package woowacourse.movie.model.board

data class Position(val x: Int, val y: Int) {
    init {
        require(x >= MIN_POSITION && y >= MIN_POSITION) { "x=$x, y=$y - $MIN_POSITION 보다 작을 수 없습니다." }
    }

    companion object {
        private const val MIN_POSITION = 0
    }
}