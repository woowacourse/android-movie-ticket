package woowacourse.movie.domain.model

import java.time.LocalTime

interface ScreenTimePolicy {
    fun screeningTimes(): List<LocalTime>
}
