package woowacourse.movie.view.data

data class SeatViewData(
    val row: Int,
    val column: Int,
    val color: Int
) {
    val rowCharacter: Char
        get() = START_CHARACTER + row

    companion object {
        private const val START_CHARACTER = 'A'
    }
}
