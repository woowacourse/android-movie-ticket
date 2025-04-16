package woowacourse.movie.view.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.convertLocalDateFormat(): String =
    this
        .format(DateTimeFormatter.ofPattern("yyyy.M.d"))
        .replace("-", ".")
