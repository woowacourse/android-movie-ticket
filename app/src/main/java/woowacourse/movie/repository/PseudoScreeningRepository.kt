package woowacourse.movie.repository

import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.model.screening.ScreeningDate
import java.time.LocalDate

object PseudoScreeningRepository : ScreeningRepository {
    private val pseudoMovieInfo =
        MovieInfo(
            Title("차람과 하디의 진지한 여행기"),
            RunningTime(230),
            Synopsis(
                """
                synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                """.trimIndent(),
            ),
        )
    private val pseudoScreening =
        Screening(
            pseudoMovieInfo,
            ScreeningDate(LocalDate.of(2024, 2, 25)),
        )

    override fun getScreenings(): List<Screening> =
        listOf(
            pseudoScreening,
            pseudoScreening,
            pseudoScreening,
            pseudoScreening,
            pseudoScreening,
            pseudoScreening,
            pseudoScreening,
        )
}
