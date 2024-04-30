package woowacourse.movie.ui

import java.lang.IllegalStateException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class Currency private constructor() {
    companion object {
        private val KOREA_CURRENCY_FORMAT = DecimalFormat("#,###원")

        private var MU_FORMAT: MutableMap<String, NumberFormat> =
            mutableMapOf(
                Locale.KOREA.country to KOREA_CURRENCY_FORMAT,
            )

        fun of(country: String): NumberFormat {
            if (MU_FORMAT[country] == null) {
                MU_FORMAT[country] = NumberFormat.getCurrencyInstance(Locale.getDefault())
            }
            return MU_FORMAT[country] ?: throw IllegalStateException("Not found country")
        }
    }
}
