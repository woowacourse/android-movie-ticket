package woowacourse.movie.model.screening

import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import java.io.Serializable
import java.time.LocalDate

class Screening(val movie: MovieInfo, val charge: Int = 13000) : Serializable {
    companion object {
        val default =
            Screening(
                MovieInfo(
                    Title("default"),
                    MovieDate(LocalDate.of(2222, 2, 2)),
                    RunningTime(222),
                    Synopsis("defaultSynopsis"),
                ),
            )
    }
}
