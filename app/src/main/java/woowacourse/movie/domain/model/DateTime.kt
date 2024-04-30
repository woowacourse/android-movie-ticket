package woowacourse.movie.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class DateTime(
    val date: LocalDate,
    val time: LocalTime,
) {
    companion object {
        val NULL = DateTime(LocalDate.now(), LocalTime.now())
    }
}
