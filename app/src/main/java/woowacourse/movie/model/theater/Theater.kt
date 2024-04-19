package woowacourse.movie.model.theater

import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import java.io.Serializable
import java.time.LocalDate

class Theater(val movie: MovieInfo, val charge: Int = 13000) : Serializable {
    companion object {
        val default = Theater(
            MovieInfo(
                Title("default"),
                MovieDate(LocalDate.of(2222, 2, 2)),
                RunningTime(222),
                Synopsis("defaultSynopsis")
            )
        )
    }
}
