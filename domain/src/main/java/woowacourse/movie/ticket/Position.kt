package woowacourse.movie.ticket

data class Position(
    val column: Int,
    val row: Int,
) {
    init {
        require(column > 0) { "좌석은 1행 부터 존재합니다. 현재 열: $column" }
        require(row > 0) { "좌석은 1열 부터 존재합니다. 현재 열: $column" }
    }
}
