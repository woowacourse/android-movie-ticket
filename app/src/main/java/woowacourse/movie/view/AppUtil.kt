package woowacourse.movie.view

import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
val DECIMAL_FORMAT = DecimalFormat("#,###")
