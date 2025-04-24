package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTime
import woowacourse.movie.domain.ScreeningDate
import java.time.LocalDate

object DefaultMovies {
    private val startDate: LocalDate = LocalDate.of(2025, 4, 1)
    private val endDate: LocalDate = LocalDate.of(2025, 4, 28)

    val movies =
        listOf(
            Movie(
                "해리 포터와 마법사의 돌",
                ScreeningDate(startDate, endDate),
                RunningTime(152),
                R.drawable.harry_potter_01,
            ),
            Movie(
                "해리 포터와 비밀의 방",
                ScreeningDate(startDate, endDate),
                RunningTime(152),
                R.drawable.harry_potter_02,
            ),
            Movie(
                "해리 포터와 아즈카반의 죄수",
                ScreeningDate(startDate, endDate),
                RunningTime(152),
                R.drawable.harry_potter_03,
            ),
            Movie(
                "해리 포터와 불의 잔",
                ScreeningDate(startDate, endDate),
                RunningTime(152),
                R.drawable.harry_potter_04,
            ),
            Movie(
                "해리 포터와 불사조 기사단",
                ScreeningDate(startDate, endDate),
                RunningTime(152),
                R.drawable.harry_potter_05,
            ),
            Movie(
                "해리 포터와 혼혈 왕자",
                ScreeningDate(startDate, endDate),
                RunningTime(152),
                R.drawable.harry_potter_06,
            ),
            Movie(
                "해리 포터와 죽음의 성물 1부",
                ScreeningDate(startDate, endDate),
                RunningTime(152),
                R.drawable.harry_potter_07,
            ),
            Movie(
                "해리 포터와 죽음의 성물 2부",
                ScreeningDate(startDate, endDate),
                RunningTime(152),
                R.drawable.harry_potter_08,
            ),
        )
}
