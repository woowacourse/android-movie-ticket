package woowacourse.movie.model.movie

import java.time.LocalDate

class Movie(
    val movieDetail: MovieDetail,
    val screeningDate: ScreeningDate,
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
                ScreeningDate(LocalDate.of(2222, 2, 2)),
            )
    }
}
