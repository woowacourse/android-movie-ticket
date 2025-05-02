package woowacourse.movie.domain

data class Seat(val column: Column, val row: Row) {
    constructor(seat: String) : this(Column((seat[0])), Row(seat[1].toString().toInt()))
    fun price(): Int = column.price()
    fun string(): String = column.value.toString() + row.value.toString()
}
