package woowacourse.movie.utils

import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatScreeningPeriod(localDateTimes: List<LocalDateTime>): String {
    return formatToLocalDate(localDateTimes.first()) + "~" + formatToLocalDate(localDateTimes.last())
}

fun formatToLocalDate(localDateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    return localDateTime.format(formatter)
}

fun formatLocalDateTime(localDateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return localDateTime.format(formatter)
}

fun formatCurrency(amount: Int): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(amount.toLong())
}

fun mapSeatNumberToLetter(number: Int): String {
    val row =
        when (number / 4) {
            0 -> "A"
            1 -> "B"
            2 -> "C"
            3 -> "D"
            else -> "E"
        }

    val col =
        when (number % 4) {
            0 -> "1"
            1 -> "2"
            2 -> "3"
            else -> "4"
        }
    return row + col
}
