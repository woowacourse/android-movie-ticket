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

fun formatCurrency(amount: Int): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(amount.toLong())
}
