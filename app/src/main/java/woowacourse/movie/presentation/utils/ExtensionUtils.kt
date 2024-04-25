package woowacourse.movie.presentation.utils

import android.content.Context
import woowacourse.movie.R
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Int.currency(context: Context): String {
    val amount =
        when (Locale.getDefault().country) {
            Locale.KOREA.country -> context.getString(R.string.price_format_kor, this)
            else -> NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)
        }

    return context.getString(R.string.reserve_amount, amount)
}

fun LocalDateTime.toScreeningDate(): String = this.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
