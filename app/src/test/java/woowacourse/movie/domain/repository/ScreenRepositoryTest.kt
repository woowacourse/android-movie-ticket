package woowacourse.movie.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Screen

class ScreenRepositoryTest {
    private lateinit var repository: ScreenRepository

    @BeforeEach
    fun setUp() {
        repository = DummyScreens()
    }

    @Test
    fun `상영 정보들을 불러온다`() {
        // given & when
        val screens = repository.load()
        val actual = listOf(
            Screen(
                id = 1,
                Movie(
                    "해리 포터와 마법사의 돌",
                    152,
                    R.drawable.img_poster,
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " + "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                ),
                "2024-03-01",
                13_000,
            )
        )

        // then
        assertThat(screens).isEqualTo(actual)

    }

    @Test
    fun `상영 ID를 통해 상영 정보를 불러온다`() {
        // given & when
        val screen = repository.findById(1).getOrThrow()
        val actual =
            Screen(
                id = 1,
                Movie(
                    "해리 포터와 마법사의 돌",
                    152,
                    R.drawable.img_poster,
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " + "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                ),
                "2024-03-01",
                13_000,
            )

        // then
        assertThat(screen).isEqualTo(actual)
    }

    @Test
    fun `잘못된 상영 ID를 통해 상영 정보를 불러오면 NoSuchElementException 예외가 발생한다`() {
        // given & when
        val result = repository.findById(2)

        // then
        assertThrows<NoSuchElementException> {
            result.getOrThrow()
        }
    }
}