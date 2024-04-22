package woowacourse.movie

import org.assertj.core.api.Assertions.LOCAL_DATE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movie.Movie
import java.time.LocalDate

class MovieTest {
    @Test
    fun `Movie 객체의 screenPeriod 크기가 1 일때, screenPeroidToString 함수를 호출하면 해당일자를 문자열로 반환한다` () {
        val movie = MOCK_MOVIE_WITH_ONE_LOCAL_DATE
        assertThat(movie.screenPeriodToString()).isEqualTo("2024.4.1")
    }

    @Test
    fun `Movie 객체의 screenPeriod 크기가 2 일때, screenPeroidToString 함수를 호출하면 해당기간을 문자열로 반환한다` () {
        val movie = MOCK_MOVIE_WITH_TWO_LOCAL_DATE
        assertThat(movie.screenPeriodToString()).isEqualTo("2024.4.1~2024.4.30")
    }

    companion object {
        private val MOCK_MOVIE_WITH_ONE_LOCAL_DATE =
            Movie(
                id = 1,
                title = "mockTitle",
                runningTime = 100,
                screenPeriod = listOf(LocalDate.of(2024, 4, 1)),
                description = "mock description",
                imgSrc = 0,
            )
        private val MOCK_MOVIE_WITH_TWO_LOCAL_DATE =
            Movie(
                id = 1,
                title = "mockTitle",
                runningTime = 100,
                screenPeriod = listOf(
                    LocalDate.of(2024, 4, 1),
                    LocalDate.of(2024,4,30),
                    ),
                description = "mock description",
                imgSrc = 0,
            )
    }
}