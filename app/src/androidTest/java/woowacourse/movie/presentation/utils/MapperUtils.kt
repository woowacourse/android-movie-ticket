package woowacourse.movie.presentation.utils

import android.content.Context
import woowacourse.movie.R
import woowacourse.movie.domain.model.Reservation
import java.text.NumberFormat
import java.util.Locale

fun Reservation.currency(context: Context): String {
    val amount =
        when (Locale.getDefault().country) {
            Locale.KOREA.country -> context.getString(R.string.price_format_kor, totalPrice)
            else -> NumberFormat.getCurrencyInstance(Locale.getDefault()).format(totalPrice)
        }

    return context.getString(R.string.reserve_amount, amount)
}
