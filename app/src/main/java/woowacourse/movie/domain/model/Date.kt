package woowacourse.movie.domain.model

import java.time.LocalDate
import java.time.format.DateTimeParseException

data class Date(
    private val year: Int,
    private val month: Int,
    private val day: Int,
) {
    init {
        require(year in YEAR_RANGE) { INVALID_YEAR_MESSAGE.format(year) }
        require(isValidDate(year, month, day)) { INVALID_DATE_FORMAT_MESSAGE.format(this) }
    }

    override fun toString(): String {
        return String.format("%04d. %02d. %02d", year, month, day)
    }

    companion object {
        private const val DATE_PARTS_SIZE = 3
        private const val SPLIT_DELIMITER = "."
        private const val INVALID_YEAR_MESSAGE = "잘못된 연도 형식: %s"
        private const val INVALID_DATE_FORMAT_MESSAGE = "잘못된 날짜 형식: %s"
        private val YEAR_RANGE = 1900..2100

        fun from(date: String): Date {
            val dateParts = date.split(SPLIT_DELIMITER)
            require(dateParts.size == DATE_PARTS_SIZE) { INVALID_DATE_FORMAT_MESSAGE.format(date) }
            val (year, month, day) = dateParts
            return Date(year.toInt(), month.toInt(), day.toInt())
        }

        private fun isValidDate(
            year: Int,
            month: Int,
            day: Int,
        ): Boolean {
            return try {
                LocalDate.of(year, month, day)
                true
            } catch (e: DateTimeParseException) {
                false
            }
        }
    }
}
