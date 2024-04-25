package woowacourse.movie.model.screenTime

import java.time.LocalDate
import java.time.LocalTime

object Weekday : ScreeningPolicy {
    private val SCREEN_TIMES = 10 until 24
    private const val DEFAULT_MINUTE: Int = 0
    private const val INTERVAL_TIME: Int = 2

    override fun screenTimes(movieScreenDay: LocalDate): List<LocalTime> {
        val screenTimes = mutableListOf<LocalTime>()
        SCREEN_TIMES.step(INTERVAL_TIME)
            .map { screenTimes.add(LocalTime.of(it, DEFAULT_MINUTE)) }

        return screenTimes
    }
}
