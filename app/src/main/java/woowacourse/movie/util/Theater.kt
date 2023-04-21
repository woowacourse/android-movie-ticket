package woowacourse.movie.util

import woowacourse.movie.domain.policy.MorningPolicy
import woowacourse.movie.domain.policy.MovieDayPolicy
import woowacourse.movie.domain.policy.NightPolicy
import woowacourse.movie.domain.seat.Grade
import woowacourse.movie.domain.theater.TheaterInfo

object Theater {
    private val rowGrade = mapOf(
        0 to Grade.B,
        1 to Grade.B,
        2 to Grade.S,
        3 to Grade.S,
        4 to Grade.A,
    )
    val row = 5
    val col = 4

    val info = TheaterInfo(rowGrade, row, col)
    val policies = listOf(
        MovieDayPolicy(),
        MorningPolicy(),
        NightPolicy(),
    )
}
