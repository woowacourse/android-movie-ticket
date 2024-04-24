package woowacourse.movie.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.utils.getDummyScreen

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
        val screen = getDummyScreen()
        val actual = listOf(screen)

        // then
        assertThat(screens).isEqualTo(actual)
    }

    @Test
    fun `상영 ID를 통해 상영 정보를 불러온다`() {
        // given & when
        val screen = repository.findByScreenId(1).getOrThrow()
        val actual = getDummyScreen()

        // then
        assertThat(screen).isEqualTo(actual)
    }

    @Test
    fun `잘못된 상영 ID를 통해 상영 정보를 불러오면 NoSuchElementException 예외가 발생한다`() {
        // given & when
        val result = repository.findByScreenId(2)

        // then
        assertThrows<NoSuchElementException> {
            result.getOrThrow()
        }
    }
}
