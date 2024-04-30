package woowacourse.movie.model.screening

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.R
import java.time.LocalDate

class ScreeningTest {
    @Test
    fun `상영_정보_객체_생성_시_제공한_영화_아이디에_따른_영화_데이터를_보유한다`() {
        // given
        val screening =
            Screening.of(
                0,
                Movie(
                    movieId = 0,
                    title = "해리 포터와 마법사의 돌",
                    thumbnailResourceId = R.drawable.thumbnail_movie1,
                    runningTime = 152,
                    introduction =
                        """
                        《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                        """.trimIndent(),
                ),
                DatePeriod(
                    startDate = LocalDate.of(2024, 3, 1),
                    endDate = LocalDate.of(2024, 3, 15),
                    dateSpan = 1,
                ),
            )

        // then
        assertEquals(
            Movie(
                movieId = 0,
                title = "해리 포터와 마법사의 돌",
                thumbnailResourceId = R.drawable.thumbnail_movie1,
                runningTime = 152,
                introduction =
                    """
                    《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                    """.trimIndent(),
            ),
            screening.movie,
        )
    }

    @Test
    fun `상영_정보에_따른_올바른_기간들을_반환한다`() {
        // given
        val screening =
            Screening.of(
                0,
                Movie(
                    movieId = 1,
                    title = "해리 포터와 비밀의 방",
                    thumbnailResourceId = R.drawable.thumbnail_movie2,
                    runningTime = 162,
                    introduction =
                        """
                        《해리 포터와 비밀의 방》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.
                        """.trimIndent(),
                ),
                DatePeriod(
                    startDate = LocalDate.of(2024, 3, 1),
                    endDate = LocalDate.of(2024, 3, 15),
                    dateSpan = 1,
                ),
            )

        // then
        assertEquals(
            listOf(
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 2),
                LocalDate.of(2024, 3, 3),
                LocalDate.of(2024, 3, 4),
                LocalDate.of(2024, 3, 5),
                LocalDate.of(2024, 3, 6),
                LocalDate.of(2024, 3, 7),
                LocalDate.of(2024, 3, 8),
                LocalDate.of(2024, 3, 9),
                LocalDate.of(2024, 3, 10),
                LocalDate.of(2024, 3, 11),
                LocalDate.of(2024, 3, 12),
                LocalDate.of(2024, 3, 13),
                LocalDate.of(2024, 3, 14),
                LocalDate.of(2024, 3, 15),
            ),
            screening.dates,
        )
    }
}
