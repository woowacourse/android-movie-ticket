package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

class DateRange(val startDate: LocalDate, val endDate: LocalDate) : Serializable
