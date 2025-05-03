package woowacourse.movie.util

import android.icu.text.DecimalFormat

object StringFormatter {
    fun toPriceFormat(price: Int): String {
        return DecimalFormat("#,###").format(price)
    }
}
