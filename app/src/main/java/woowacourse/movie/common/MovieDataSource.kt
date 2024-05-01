package woowacourse.movie.common

import woowacourse.movie.R
import woowacourse.movie.list.model.Movie
import java.time.LocalDate

object MovieDataSource {
    private val dummyMovie =
        Movie(
            "",
            0,
            LocalDate.of(0, 1, 1),
            0,
            "",
            -1,
        )

    val movieList =
        listOf(
            Movie(
                "해리 포터와 마법사의 돌",
                R.drawable.harry_potter_1_poster,
                LocalDate.of(2024, 3, 1),
                152,
                """
                《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                """.trimIndent(),
                0,
            ),
            Movie(
                "해리 포터와 비밀의 방",
                R.drawable.harry_potter_2_poster,
                LocalDate.of(2024, 4, 1),
                152,
                """
                해리포터와 비밀의 방이다.
                """.trimIndent(),
                1,
            ),
            Movie(
                "해리 포터와 아즈카반의 죄수",
                R.drawable.harry_potter_3_poster,
                LocalDate.of(2024, 5, 1),
                152,
                """
                해리포터와 아즈카반의 죄수다.
                """.trimIndent(),
                2,
            ),
            dummyMovie,
            Movie(
                "해리 포터와 불의 잔",
                R.drawable.harry_potter_4_poster,
                LocalDate.of(2024, 6, 1),
                152,
                """
                해리포터와 불의 잔이다.
                """.trimIndent(),
                3,
            ),
            Movie(
                "해리 포터와 불사조 기사단",
                R.drawable.harry_potter_5_poster,
                LocalDate.of(2024, 7, 1),
                152,
                """
                해리포터와 불사조 기사단이다.
                """.trimIndent(),
                4,
            ),
            Movie(
                "해리 포터와 혼혈 왕자",
                R.drawable.harry_potter_6_poster,
                LocalDate.of(2024, 8, 1),
                152,
                """
                해리포터와 혼혈왕자다.
                """.trimIndent(),
                5,
            ),
            dummyMovie,
            Movie(
                "해리 포터와 죽음의 성물",
                R.drawable.harry_potter_7_poster,
                LocalDate.of(2024, 9, 1),
                152,
                """
                해리포터와 죽음의 성물
                """.trimIndent(),
                6,
            ),
        )
}
