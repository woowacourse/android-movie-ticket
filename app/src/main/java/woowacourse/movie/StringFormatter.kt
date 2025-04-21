package woowacourse.movie

import android.icu.text.DecimalFormat
import woowacourse.movie.domain.Time

object StringFormatter {
    fun formatTime(time: Time): String = "%02d:%02d".format(time.value.hour, time.value.minute)

    fun formatMoney(amount: Int): String = DecimalFormat("#,###").format(amount)
}
