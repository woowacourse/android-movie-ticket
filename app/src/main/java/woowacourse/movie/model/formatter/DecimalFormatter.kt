package woowacourse.movie.model.formatter

import java.text.DecimalFormat

object DecimalFormatter : Formatter<Int>() {
    override val formatString: String = "#,###"

    override fun format(data: Int): String {
        val decimalFormatter = DecimalFormat(formatString)
        return decimalFormatter.format(data)
    }
}
