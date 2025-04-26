package woowacourse.movie

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
val APRIL_THIRTIETH: LocalDate = LocalDate.parse("2025.04.30", formatter)
val MAY_FIRST: LocalDate = LocalDate.parse("2025.05.01", formatter)
val MAY_SECOND: LocalDate = LocalDate.parse("2025.05.02", formatter)
val MAY_THIRD: LocalDate = LocalDate.parse("2025.05.03", formatter)
val ON_TIME: LocalTime = LocalTime.of(12, 0)
val LAST_MOVIE_TIME: LocalTime = LocalTime.of(22, 0)
