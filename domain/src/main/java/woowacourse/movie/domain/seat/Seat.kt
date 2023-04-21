package woowacourse.movie.domain.seat

data class Seat(val row: Int, val col: Int) {
    init {
        require(row >= 0 && col >= 0) { RANGE_ERROR_MESSAGE }
    }

    companion object {
        private const val RANGE_ERROR_MESSAGE = "행과 열의 값은 음수가 될 수 없습니다."
    }
}
