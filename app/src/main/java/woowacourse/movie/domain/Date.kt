package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

class Date(
    val startDate: LocalDate,
    val endDate: LocalDate,
) : Serializable
