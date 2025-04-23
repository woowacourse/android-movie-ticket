package woowacourse.movie

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
val APRIL_THIRTIETH: LocalDate = LocalDate.parse("2025.04.30", formatter)
val MAY_FOURTH: LocalDate = LocalDate.parse("2025.05.04", formatter)
val ON_TIME: LocalTime = LocalTime.of(12, 0)
