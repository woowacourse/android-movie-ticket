package woowacourse.movie.utils

import java.text.DecimalFormat

class PriceFormatter {
    fun format(price: Int): String {
        val priceFormatter = DecimalFormat(PRICE_PATTERN)
        return priceFormatter.format(price).toString()
    }

    companion object {
        private const val PRICE_PATTERN = "#,###"
    }
}