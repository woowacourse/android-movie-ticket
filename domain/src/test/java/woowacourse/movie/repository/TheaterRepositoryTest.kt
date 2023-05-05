package woowacourse.movie.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.theater.Theater

class TheaterRepositoryTest {

    @Test
    fun `아이디가 없는 극장을 저장하면 자동으로 아이디가 부여된다`() {
        val theater = Theater(5, 4)

        TheaterRepository.save(theater)

        assertThat(theater.id).isNotNull
    }

    @Test
    fun `생성되면 샘플 데이터 1개 이상 저장한다`() {
        val actual = TheaterRepository.findAll()

        assertThat(actual).hasSizeGreaterThanOrEqualTo(1)
    }
}
