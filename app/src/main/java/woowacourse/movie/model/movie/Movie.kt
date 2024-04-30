package woowacourse.movie.model.movie

import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningPeriod
import java.time.LocalDate

class Movie(
    val title: Title,
    val runningTime: RunningTime,
    val screeningPeriod: ScreeningPeriod,
    val synopsis: Synopsis,
) {
    companion object {
        val default =
            Movie(
                Title("default"),
                RunningTime(222),
                ScreeningPeriod(
                    ScreeningDate(LocalDate.of(1111, 1, 1)),
                    ScreeningDate(LocalDate.of(2222, 2, 2)),
                ),
                Synopsis("defaultSynopsis"),
            )
    }
}
