package woowacourse.movie.repository

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.Month

class MovieRepositoryTest {

    @Test
    fun `영화 저장소가 생성되면 아이디가 1이고 상영일이 2024년 3월인 영화가 추가된다`() {
        val movieId = 1L
        val movie = MovieRepository.findById(movieId)


        val screeningDates =
            movie!!.screeningInfos.map { it.screeningDateTime.toLocalDate() }.toSet()
        assertAll(
            { assertThat(screeningDates).hasSize(31) },
            { assertThat(screeningDates).allMatch { it.year == 2024 && it.month == Month.MARCH } }
        )
    }
}
