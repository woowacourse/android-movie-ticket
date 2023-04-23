package woowacourse.movie.domain

import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationAgencyTest {

    @Test
    fun `선택한 좌석 수와 예매 인원이 일치하면 예매할 수 있다`() {
        val reservationAgency = ReservationAgency(
            getAnyMovie(),
            2,
            LocalDateTime.now()
        )
        val actual = reservationAgency.canReserve(
            listOf(
                Seat(1, 1), Seat(1, 2)
            )
        )
        assert(actual)
    }

    @Test
    fun `선택한 좌석은 2개 예매 인원은 3명이면 예매할 수 없다`() {
        val reservationAgency = ReservationAgency(
            getAnyMovie(),
            3,
            LocalDateTime.now()
        )
        val actual = reservationAgency.canReserve(
            listOf(
                Seat(1, 1), Seat(1, 2)
            )
        )
        assert(!actual)
    }

    @Test
    fun `할인 조건에 해당하지 않고, B등급 2자리를 선택했다면 금액은 20000이다`() {
        val reservationAgency = ReservationAgency(
            getAnyMovie(),
            2,
            LocalDateTime.of(LocalDate.of(2023, 3, 7), LocalTime.of(12, 0))
        )

        val actual = reservationAgency.calculateReservationFee(
            listOf(
                Seat(1, 1), Seat(1, 2)
            )
        ).amount

        Assert.assertEquals(20000, actual)
    }

    @Test
    fun `할인 조건에 해당하지 않고, S등급 2자리를 선택했다면 금액은 30000이다`() {
        val reservationAgency = ReservationAgency(
            getAnyMovie(),
            2,
            LocalDateTime.of(LocalDate.of(2023, 3, 7), LocalTime.of(12, 0))
        )

        val actual = reservationAgency.calculateReservationFee(
            listOf(
                Seat(1, 3), Seat(2, 3)
            )
        ).amount

        Assert.assertEquals(30000, actual)
    }

    @Test
    fun `무비데이이고 S등급 2자리이면 금액은 27000원이다`() {
        val selectedDate = LocalDate.of(2023, 3, 10)
        val reservationAgency = ReservationAgency(
            getAnyMovie(),
            2,
            LocalDateTime.of(selectedDate, LocalTime.of(12, 0))
        )

        val actual = reservationAgency.calculateReservationFee(
            listOf(
                Seat(1, 3), Seat(2, 3)
            )
        ).amount

        Assert.assertEquals(27000, actual)
    }

    @Test
    fun `조조이고 S등급 2자리이면 금액은 26000원이다`() {
        val selectedDate = LocalDate.of(2023, 3, 11)
        val reservationAgency = ReservationAgency(
            getAnyMovie(),
            2,
            LocalDateTime.of(selectedDate, LocalTime.of(10, 0))
        )

        val actual = reservationAgency.calculateReservationFee(
            listOf(
                Seat(1, 3), Seat(2, 3)
            )
        ).amount

        Assert.assertEquals(26000, actual)
    }

    @Test
    fun `조조이고 무비데이이고 S등급 2자리이면 금액은 23000원이다`() {
        val selectedDate = LocalDate.of(2023, 3, 10)
        val reservationAgency = ReservationAgency(
            getAnyMovie(),
            2,
            LocalDateTime.of(selectedDate, LocalTime.of(10, 0))
        )

        val actual = reservationAgency.calculateReservationFee(
            listOf(
                Seat(1, 3), Seat(2, 3)
            )
        ).amount

        Assert.assertEquals(23000, actual)
    }

    private fun getAnyMovie(): Movie =
        Movie(
            "아바타",
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2023, 3, 31),
            Minute(120),
            1,
            "줄거리"
        )
}
