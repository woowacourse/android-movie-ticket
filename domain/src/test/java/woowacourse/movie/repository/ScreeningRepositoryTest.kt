package woowacourse.movie.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.screening.Minute
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.domain.screening.ScreeningRange
import woowacourse.movie.domain.theater.Theater
import java.time.LocalDate

internal class ScreeningRepositoryTest {

    @Test
    fun `아이디가 없는 상영을 저장하면 자동으로 아이디가 부여된다`() {
        val screeningRange = ScreeningRange(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 31))
        val theater = Theater(5, 4)
        val movie = Movie("title", Minute(152), "summary")
        val screening = Screening(screeningRange, theater, movie)

        ScreeningRepository.save(screening)

        assertThat(screening.id).isNotNull
    }

    @Test
    fun `생성되면 샘플 데이터 4개 이상 저장한다`() {
        val actual = ScreeningRepository.findAll()

        assertThat(actual).hasSizeGreaterThanOrEqualTo(4)
    }
}
