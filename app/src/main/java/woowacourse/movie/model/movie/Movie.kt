package woowacourse.movie.model.movie

import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningPeriod
import java.time.LocalDate

class Movie(
    val movieDetail: MovieDetail,
    val screeningPeriod: ScreeningPeriod,
    val charge: Int = 13000
) {
    companion object {
        val default =
            Movie(
                MovieDetail(
                    Title("default"),
                    RunningTime(222),
                    Synopsis("defaultSynopsis"),
                ),
                ScreeningPeriod(
                    ScreeningDate(LocalDate.of(1111, 1, 1)) ,
                    ScreeningDate(LocalDate.of(2222, 2, 2)) ,
                ),
            )
    }
}
