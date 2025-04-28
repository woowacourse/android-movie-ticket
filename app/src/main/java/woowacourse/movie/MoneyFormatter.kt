package woowacourse.movie

import android.icu.text.DecimalFormat

object MoneyFormatter {
    fun formatMoney(amount: Int): String = DecimalFormat("#,###").format(amount)
}
