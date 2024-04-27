package woowacourse.movie.domain.screening

import java.time.LocalDate
import java.time.LocalTime

data class DailySchedule(val date: LocalDate, val times: List<LocalTime>)
