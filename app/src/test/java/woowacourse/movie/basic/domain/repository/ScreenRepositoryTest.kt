package woowacourse.movie.basic.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.basic.utils.getDummyScreen
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.domain.repository.ScreenRepository

class ScreenRepositoryTest {
    private lateinit var repository: ScreenRepository

    @BeforeEach
    fun setUp() {
        repository = DummyScreens()
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
        val result = repository.findByScreenId(-1)

        // then
        assertThrows<NoSuchElementException> {
            result.getOrThrow()
        }
    }
}
