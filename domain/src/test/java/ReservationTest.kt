package woowacourse.movie.domain

import org.junit.Assert.assertThrows
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

class ReservationTest() {

    @Test
    fun `예매 인원이 0명이면 에러가 발생한다`() {
        assertThrows(
            "[ERROR] 예매 인원은 최소 1명 이상 최대 200명 이하여야 합니다.",
            IllegalArgumentException::class.java
        ) {
            Reservation(getAnyMovie(), listOf(), LocalDateTime.now(), Money(0))
        }
    }

    @Test
    fun `예매 인원이 21명이면 에러가 발생한다`() {
        assertThrows(
            "[ERROR] 예매 인원은 최소 1명 이상 최대 20명 이하여야 합니다.",
            IllegalArgumentException::class.java
        ) {
            Reservation(
                getAnyMovie(),
                List<Seat>(21) { Seat(1, 1) },
                LocalDateTime.now(),
                Money(0)
            )
        }
    }

    @Test
    fun `상영 기간이 2일부터 31일인데 범위를 벗어난 1일에 예약하면 에러가 발생한다`() {
        val movie = Movie(
            "아바타",
            LocalDate.of(2024, 3, 2),
            LocalDate.of(2024, 3, 31),
            Minute(120),
            1,
            "줄거리"
        )

        assertThrows(IllegalArgumentException::class.java) {
            Reservation(
                movie,
                listOf(Seat(1, 1)),
                LocalDateTime.of(2024, 3, 1, 12, 0),
                Money(10000)
            )
        }
    }

    private fun getAnyMovie(): Movie =
        Movie(
            "아바타",
            LocalDate.now(),
            LocalDate.now().plusDays(1),
            Minute(120),
            1,
            "줄거리"
        )
}
