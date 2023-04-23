package woowacourse.movie.fomatter

import com.example.domain.model.Money
import java.text.DecimalFormat

class MoneyFormatter {
    fun active(totalMoney: Money): String {
        return DECIMAL_FORMATTER.format(totalMoney.value)
    }

    companion object {
        private val DECIMAL_FORMATTER = DecimalFormat("#,###")
    }
}
