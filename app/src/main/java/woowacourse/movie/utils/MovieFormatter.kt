package woowacourse.movie.utils

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTimestamp(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("yyyy.M.d", Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}

fun formatCurrency(amount: Int): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(amount.toLong())
}
