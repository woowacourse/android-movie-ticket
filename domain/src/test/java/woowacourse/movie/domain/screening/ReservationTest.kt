package woowacourse.movie.domain.screening

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.discount.Money
import woowacourse.movie.domain.theater.Point
import woowacourse.movie.domain.theater.Theater
import java.time.LocalDateTime

internal class ReservationTest {


    @Test
    fun `예매를 생성할 때 어떠한 좌석도 예매하지 않으면 에러가 발생한다`() {
        val movie = Movie("제목", Minute(152), "요약")
        val theater = Theater(5, 5)
        val emptySeatPoints = listOf<Point>()

        assertThatIllegalArgumentException()
            .isThrownBy { Reservation(movie, LocalDateTime.now(), theater, emptySeatPoints) }
            .withMessage("최소 한 개 이상의 좌석을 예매해야 합니다.")
    }

    @Test
    fun `예매를 생성할 때 예매 좌석이 극장 내에 존재하지 않으면 에러가 발생한다`() {
        val movie = Movie("제목", Minute(152), "요약")
        val theater = Theater(5, 5)
        val notExistSeatPoint = Point(6, 6)

        assertThatIllegalArgumentException()
            .isThrownBy { Reservation(movie, LocalDateTime.now(), theater, listOf(notExistSeatPoint)) }
            .withMessage("예매하려는 좌석이 존재하지 않습니다.")
    }

    @Test
    fun `예매 요금은 각 예매 좌석 요금에 할인을 적용한 금액의 총 합이다`() {
        val movie = Movie("제목", Minute(152), "요약")
        val screeningDateTime = LocalDateTime.of(2024, 3, 10, 10, 0)
        val theater = Theater(5, 4)
        val seatPoints = listOf(Point(1, 1), Point(3, 1))
        val reservation = Reservation(movie, screeningDateTime, theater, seatPoints)

        val actual = reservation.fee

        assertThat(actual).isEqualTo(Money(18_500))
    }
}
