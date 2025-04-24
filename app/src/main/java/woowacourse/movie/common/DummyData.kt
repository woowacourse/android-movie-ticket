package woowacourse.movie.common

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import java.time.LocalDate

object DummyData {
    val movies: Map<Movie, Int> =
        mapOf(
            Movie(
                title = "해리 포터와 마법사의 돌",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 30),
                runningTime = 152,
                poster = R.drawable.harry_potter_01,
            ) to R.drawable.harry_potter_01,
            Movie(
                title = "해리 포터와 비밀의 방",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 30),
                runningTime = 161,
                poster = R.drawable.harry_potter_01,
            ) to R.drawable.harry_potter_02,
            Movie(
                title = "해리 포터와 아즈카반의 죄수",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 30),
                runningTime = 141,
                poster = R.drawable.harry_potter_01,
            ) to R.drawable.harry_potter_03,
            Movie(
                title = "해리 포터와 불의 잔",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 30),
                runningTime = 157,
                poster = R.drawable.harry_potter_01,
            ) to R.drawable.harry_potter_04,
            Movie(
                title = "해리 포터와 불사조 기사단",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 30),
                runningTime = 138,
                poster = R.drawable.harry_potter_01,
            ) to R.drawable.harry_potter_05,
            Movie(
                title = "해리 포터와 혼혈 왕자",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 30),
                runningTime = 153,
                poster = R.drawable.harry_potter_01,
            ) to R.drawable.harry_potter_06,
            Movie(
                title = "해리 포터와 죽음의 성물 - 1부",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 30),
                runningTime = 146,
                poster = R.drawable.harry_potter_01,
            ) to R.drawable.harry_potter_07,
            Movie(
                title = "해리 포터와 죽음의 성물 - 2부",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 30),
                runningTime = 131,
                poster = R.drawable.harry_potter_01,
            ) to R.drawable.harry_potter_08,
        )
}
