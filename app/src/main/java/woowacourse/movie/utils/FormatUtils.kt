package woowacourse.movie.utils

import java.text.DecimalFormat

object FormatUtils {
    private val decimalFormat = DecimalFormat("#,###")

    fun formatCurrency(value: Int): String {
        return "${decimalFormat.format(value)}원"
    }
}
