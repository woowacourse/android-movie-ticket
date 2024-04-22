package woowacourse.movie.repository

import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.screening.Screening
import java.time.LocalDate

class PseudoScreeningRepository : ScreeningRepository {
    private val pseudoMovieInfo =
        MovieInfo(
            Title("차람과 하디의 진지한 여행기"),
            MovieDate(LocalDate.of(2024, 2, 25)),
            RunningTime(230),
            Synopsis("wow!"),
        )

    override fun getScreenings(): List<Screening> =
        listOf(
            Screening(pseudoMovieInfo),
            Screening(pseudoMovieInfo),
            Screening(pseudoMovieInfo),
            Screening(pseudoMovieInfo),
            Screening(pseudoMovieInfo),
            Screening(pseudoMovieInfo),
            Screening(pseudoMovieInfo),
        )
}
