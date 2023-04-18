package woowacourse.movie.domain

import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.LocalDateTime

@RunWith(Parameterized::class)
class ReservationTest(private val illegalPeopleCount: Int) {

    @Test
    fun `예매 인원이 1명 이상 200명 이하가 아니면 에러가 발생한다`() {
        assertThrows(
            "[ERROR] 예매 인원은 최소 1명 이상 최대 200명 이하여야 합니다.",
            IllegalArgumentException::class.java
        ) {
            Reservation(LocalDateTime.now(), illegalPeopleCount)
        }
    }

    @Test
    fun `예매 금액은 영화 티켓 가격에 할인 정책이 적용된 금액 곱하기 예매 인원 수이다`() {
        val audienceCount = 4
        val reservation = Reservation(LocalDateTime.of(3021, 3, 10, 9, 0), audienceCount)

        val actual = reservation.fee

        val expected = Money(9700) * audienceCount
        assert(actual == expected)
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun peopleCount(): Collection<Int> {
            return listOf(0, 201)
        }
    }
}
