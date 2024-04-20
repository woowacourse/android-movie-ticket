package woowacourse.movie.model.screening

import java.io.Serializable
import java.time.LocalDate

data class Schedule(val date: LocalDate) : Serializable
