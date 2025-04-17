package woowacourse.movie.ui.view

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CustomFormatter {
    private val dateTimeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

    fun formatDateTime(dateTime: LocalDateTime): String = dateTime.format(dateTimeFormatter)

    fun formatHeadCount(message: String, headCount: Int): String {
        return String.format(message, headCount)
    }

    fun formatAmount(message: String, value: Int): String {
        return String.format(message, value.formatWithComma())
    }

    private fun Int.formatWithComma(): String = "%,d".format(this)
}