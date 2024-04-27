package woowacourse.movie.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MockScreeningRepositoryTest {
    @Test
    fun `모든 상영 정보를 불러올 수 있다`() {
        val screenings = MockScreeningRepository.findAll()
        val result = screenings.size
        assertThat(result).isEqualTo(4)
    }

    @Test
    fun `id를 key로 상영 정보를 불러올 수 있다`() {
        val screening = MockScreeningRepository.find(0)
        val result = screening?.id
        assertThat(result).isEqualTo(0)
    }
}
