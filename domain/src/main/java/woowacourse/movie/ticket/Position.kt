package woowacourse.movie.ticket

data class Position(
    val row: Int,
    val column: Int,
) {
    init {
        require(column >= 0) { "좌석의 행은 음수일 수 없습니다. 현재 열: $column" }
        require(row >= 0) { "좌석의 열은 음수일 수 없습니다. 현재 열: $column" }
    }
}
