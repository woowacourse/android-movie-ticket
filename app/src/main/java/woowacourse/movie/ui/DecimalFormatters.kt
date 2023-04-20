package woowacourse.movie.ui

import woowacourse.movie.model.MoneyState
import java.text.DecimalFormat

object DecimalFormatters {
    fun convertToMoneyFormat(moneyState: MoneyState): String = DECIMAL_FORMATTER.format(moneyState.price)

    private val DECIMAL_FORMATTER = DecimalFormat("#,###")
}
