package woowacourse.movie.model.screenTime

import java.time.LocalTime

interface ScreeningPolicy {

    fun screenTimes(): List<LocalTime>
}
