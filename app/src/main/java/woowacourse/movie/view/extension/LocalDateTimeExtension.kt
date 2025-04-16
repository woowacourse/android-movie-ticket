package woowacourse.movie.view.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.convertLocalDateFormat(): String =
    this
        .format(DateTimeFormatter.ofPattern("yyyy.M.d"))
        .replace("-", ".")
