package woowacourse.movie.data

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.TestFixture.MOCK_MOVIE
import java.time.LocalDateTime

class MockTicketRepositoryTest {
    @Test
    fun `id를 key로 예약을 불러올 수 있다`() {
        val movie = MockScreeningRepository.find(0)
        val result = movie?.id
        Assertions.assertThat(result).isEqualTo(0)
    }

    @Test
    fun `상영과 수량 정보로 예약을 추가할 수 있다`() {
        val size = MockTicketRepository.findAll().size
        MockTicketRepository.save(
            MOCK_MOVIE,
            LocalDateTime.now(),
            listOf(""),
            30000,
        )

        val newSize = MockTicketRepository.findAll().size
        Assertions.assertThat(newSize).isEqualTo(size + 1)
    }
}
