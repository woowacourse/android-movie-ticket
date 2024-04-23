package woowacourse.movie.model.screening

import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import java.io.Serializable
import java.time.LocalDate

class Screening(
    val movie: MovieInfo,
    val date: ScreeningDate = ScreeningDate(LocalDate.of(2222, 2, 2)),
    val charge: Int = 13000,
) : Serializable {
    companion object {
        val default =
            Screening(
                MovieInfo(
                    Title("default"),
                    RunningTime(222),
                    Synopsis("defaultSynopsis"),
                ),
            )
    }
}
