package woowacourse.movie.domain

import java.time.LocalDate
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
val APRIL_THIRTIETH: LocalDate = LocalDate.parse("2025.04.30", formatter)
val MAY_FIRST: LocalDate = LocalDate.parse("2025.05.01", formatter)
val MAY_SECOND: LocalDate = LocalDate.parse("2025.05.02", formatter)
val MAY_THIRD: LocalDate = LocalDate.parse("2025.05.03", formatter)
