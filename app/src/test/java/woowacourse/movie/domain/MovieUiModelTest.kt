package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.ui.MovieUiModel
import java.time.LocalDate
import java.time.LocalDateTime

class MovieUiModelTest {
    @Test
    fun `상영 기간을 계산해서 리스트로 반환한다`() {
        // given
        val movie = MovieUiModel(
            1,
            "해리 포터와 마법사의 돌",
            LocalDate.of(2023, 4, 12),
            LocalDate.of(2023, 4, 15),
            152,
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
            R.drawable.harry_potter_thumbnail,
            R.drawable.harry_potter_poster,
        )
        val expected = listOf<LocalDate>(
            LocalDate.of(2023, 4, 12),
            LocalDate.of(2023, 4, 13),
            LocalDate.of(2023, 4, 14),
            LocalDate.of(2023, 4, 15),
        )

        // when
        val actual = movie.screeningDates

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `날짜시간, 티켓 수를 입력하면 티켓을 반환한다`() {
        // given
        val movie = MovieUiModel(
            1,
            "해리 포터와 마법사의 돌",
            LocalDate.of(2023, 4, 12),
            LocalDate.of(2023, 4, 15),
            152,
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
            R.drawable.harry_potter_thumbnail,
            R.drawable.harry_potter_poster,
        )
        val expected = Ticket(1, LocalDateTime.of(2023, 4, 14, 10, 0), 2)

        // when
        val actual = movie.reserve(LocalDateTime.of(2023, 4, 14, 10, 0), 2)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
