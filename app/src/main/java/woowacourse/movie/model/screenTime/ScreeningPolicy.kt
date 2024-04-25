package woowacourse.movie.model.screenTime

import java.time.LocalDate
import java.time.LocalTime

interface ScreeningPolicy {

    fun screenTimes(movieScreenDay : LocalDate): List<LocalTime>
}
