package woowacourse.movie.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.model.Grade
import woowacourse.movie.domain.model.Position
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats

class ScreenRepositoryTest {
    private val repository = FakeScreenRepository()

    @Test
    fun `상영 정보들을 불러온다`() {
        // given & when
        val screens = repository.load()
        val expected =
            listOf(
                FakeScreenRepository.fakeScreen1,
                FakeScreenRepository.fakeScreen2,
                FakeScreenRepository.fakeScreen3,
            )

        // then
        assertThat(screens).isEqualTo(expected)
    }

    @Test
    fun `상영 ID를 통해 상영 정보를 불러온다`() {
        val actual = repository.findById(1).getOrThrow()
        val expected = FakeScreenRepository.fakeScreen1

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `잘못된 상영 ID를 통해 상영 정보를 불러오면 NoSuchElementException 예외가 발생한다`() {
        // given & when
        val result = repository.findById(5)

        // then
        assertThrows<NoSuchElementException> {
            result.getOrThrow()
        }
    }

    @Test
    fun `상영관의 좌석을 모두 가져온다`() {
        val actual = repository.seats(1)
        val expected =
            Seats(
                Seat(Position(0, 0), Grade.S),
                Seat(Position(1, 1), Grade.A),
                Seat(Position(2, 2), Grade.B),
            )

        assertThat(actual).isEqualTo(expected)
    }
}
