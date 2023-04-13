package woowacourse.movie.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.LocalDate
import java.time.LocalDateTime

@RunWith(Parameterized::class)
class ReservationTest(private val illegalPeopleCount: Int) {

    @Test
    fun `예매 인원이 1명 이상 200명 이하가 아니면 에러가 발생한다`() {
        assertThrows(
            "[ERROR] 예매 인원은 최소 1명 이상 최대 200명 이하여야 합니다.",
            IllegalArgumentException::class.java
        ) {
            Reservation(getAnyMovie(), illegalPeopleCount, LocalDateTime.now())
        }
    }

    @Test
    fun `처음 총 예매 금액은 인원수에 영화 티켓 한 장 가격을 곱한 값이다`() {
        val peopleCount = 2

        val actual =
            Reservation(getAnyMovie(), peopleCount, LocalDateTime.now()).initReservationFee

        assertEquals(Money(26000), actual)
    }

    @Test
    fun `예약 생성 시 상영 날짜가 영화의 상영 기간 범위를 벗어나면 에러가 발생한다`() {
        val movie = Movie(
            "아바타",
            LocalDate.of(2024, 3, 2),
            LocalDate.of(2024, 3, 31),
            Minute(120),
            Poster(1),
            MovieDetail("줄거리")
        )

        assertThrows(IllegalArgumentException::class.java) {
            Reservation(
                movie,
                3,
                LocalDateTime.of(2024, 3, 1, 12, 0)
            )
        }
    }

    private fun getAnyMovie(): Movie =
        Movie(
            "아바타",
            LocalDate.now(),
            LocalDate.now().plusDays(1),
            Minute(120),
            Poster(1),
            MovieDetail("줄거리")
        )

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun peopleCount(): Collection<Int> {
            return listOf(0, 201)
        }
    }
}
