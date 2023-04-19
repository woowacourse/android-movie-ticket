package woowacourse.movie.ui.seat

class Column private constructor(val value: Int) {
    companion object {
        fun createColumns(size: Int): List<Column> = (1..size).map(::Column)
    }
}
