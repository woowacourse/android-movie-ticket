package woowacourse.movie.view.extension

import java.time.LocalDateTime

fun LocalDateTime.convertLocalDateFormat(): String = this.toLocalDate().toString().replace("-", ".")
