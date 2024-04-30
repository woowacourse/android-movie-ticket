package woowacourse.movie.ui.utils

private const val MIN_ROW_VALUE = 'A'
private const val MIN_COL_VALUE = '1'

fun String.toPosition(): Pair<Int, Int> {
    val row = this[0] - MIN_ROW_VALUE
    val col = this[1] - MIN_COL_VALUE
    return Pair(row, col)
}

fun positionToIndex(
    row: Int,
    col: Int,
) = row * 4 + col
