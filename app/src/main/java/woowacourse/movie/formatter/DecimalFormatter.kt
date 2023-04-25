package woowacourse.movie.formatter

import java.text.DecimalFormat

object DecimalFormatter : Formatter<Int>() {
    private const val NOT_NUMBER_ERROR = "해당 문자열은 숫자로 변환될 수 없습니다."

    override fun formatToOriginal(string: String, format: String): Int {
        val formattingString = string.filter { it != ',' }
        return formattingString.toIntOrNull() ?: throw java.lang.IllegalArgumentException(
            NOT_NUMBER_ERROR
        )
    }

    override fun formatToString(data: Int, format: String): String {
        val decimalFormatter = DecimalFormat(format)
        return decimalFormatter.format(data)
    }
}
