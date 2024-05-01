package woowacourse.movie.utils

import android.os.Build
import android.os.Bundle
import java.io.Serializable
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatScreeningPeriod(localDateTimes: List<LocalDateTime>): String {
    return formatToLocalDate(localDateTimes.first()) + "~" + formatToLocalDate(localDateTimes.last())
}

fun formatToLocalDate(localDateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    return localDateTime.format(formatter)
}

fun formatLocalDateTime(localDateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return localDateTime.format(formatter)
}

fun formatCurrency(amount: Int): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(amount.toLong())
}

fun mapSeatNumberToLetter(number: Int): String {
    val row =
        when (number / 4) {
            0 -> "A"
            1 -> "B"
            2 -> "C"
            3 -> "D"
            else -> "E"
        }

    val col =
        when (number % 4) {
            0 -> "1"
            1 -> "2"
            2 -> "3"
            else -> "4"
        }
    return row + col
}

inline fun <reified T : Serializable> Bundle.getSerializableCompat(key: String): T? =
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
        else ->
            @Suppress("DEPRECATION")
            getSerializable(key)
                as? T
    }

const val SAMPLE_AD_URL =
    "https://s3-alpha-sig.figma.com/img/e1aa/0753/a796f5d7ba7df41bb1cfaa64dd2d8167?" +
        "Expires=1715558400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Ht2jSxu-~W6VC3LYqfcpwmWkfqJs9qXmBjqzUFp" +
        "kLalMeo3TFLrJlJJDwGl8EfhRtRweS5c8ZHpyg6vyaGJ-3wvGO~gjmtGyiw-LWvIfhz7QpwamHn3M~" +
        "fFOFD8SBgd~WhNsBzHV7lVT2jm3wongpDHxoJbg733-od8dYRQMJOy-YFS0OIRGSwAKsoNSEOqR7trP8tQCfQliSjo81VBqX" +
        "tutKmHXXDCPeDu-7uDCf~rnfiRmjgdQMPs8NhCniJXPuPjZnJXL0ebKO0ivMkPCXDN0HaawxE1doNn3xYtXb9" +
        "60mP9U0oTWI3am74uNyXgx5fC1wAQB0HeJy~wg9ZpjSA__"
