package woowacourse.movie.util

import java.text.DecimalFormat

object Formatter {
    fun formatPrice(price: Int): String {
        return DecimalFormat("#,###").format(price)
    }

    fun formatRow(row: Int): Char {
        return (row + 'A'.code).toChar()
    }

    fun unFormatRow(seat: String): Int {
        return seat.first() - 'A'
    }

    fun formatColumn(column: Int): String {
        return (column + 1).toString()
    }

    fun unFormatColumn(seat: String): Int {
        return seat.substring(1).toInt()
    }
}
