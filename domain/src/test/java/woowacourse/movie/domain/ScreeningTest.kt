package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

internal class ScreeningTest {

    @Test
    fun `상영의 아이디가 설정된 상태에서 아이디를 다시 변경하면 변경되지 않는다`() {
        val screening = Screening(
            ScreeningRange(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 31)),
            Theater(5, 4),
            MovieData("제목", Minute(152), "요약")
        )
        screening.id = 1L

        screening.id = 2L

        assertThat(screening.id).isEqualTo(1L)
    }

    @Test
    fun `상영하지 않는 날짜를 예매하면 에러가 발생한다`() {
        val screening = Screening(
            ScreeningRange(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 31)),
            Theater(5, 4),
            MovieData("제목", Minute(152), "요약")
        )
        val notScreeningDateTime = LocalDateTime.of(2021, 4, 1, 0, 0)

        assertThatIllegalArgumentException()
            .isThrownBy { screening.reserve(notScreeningDateTime, listOf(Point(1, 1))) }
            .withMessage("${notScreeningDateTime}에 상영하지 않습니다.")
    }

    @Test
    fun `좌석없이 예매하면 에러가 발생한다`() {
        val screening = Screening(
            ScreeningRange(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 31)),
            Theater(5, 4),
            MovieData("제목", Minute(152), "요약")
        )
        val screeningDateTime = LocalDateTime.of(2021, 3, 1, 10, 0)
        val emptySeatPoints = listOf<Point>()

        assertThatIllegalArgumentException()
            .isThrownBy { screening.reserve(screeningDateTime, emptySeatPoints) }
            .withMessage("최소 한 개 이상의 좌석을 예매해야 합니다.")
    }

    @Test
    fun `상영되는 극장 내에 존재하지 않는 좌석을 예매하면 에러가 발생한다`() {
        val screening = Screening(
            ScreeningRange(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 31)),
            Theater(5, 4),
            MovieData("제목", Minute(152), "요약")
        )
        val screeningDateTime = LocalDateTime.of(2021, 3, 1, 10, 0)
        val notExistSeatPoint = Point(6, 6)

        assertThatIllegalArgumentException()
            .isThrownBy { screening.reserve(screeningDateTime, listOf(notExistSeatPoint)) }
            .withMessage("예매하려는 좌석이 존재하지 않습니다.")
    }
}
