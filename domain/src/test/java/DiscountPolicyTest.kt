package woowacourse.movie.domain

import com.example.domain.*
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

class DiscountPolicyTest {

    @Test
    fun `무비 데이일 때 예매하면 10퍼센트 할인을 받는다`() {
        val movie = Movie(
            "아바타",
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2023, 3, 31),
            Minute(120),
            1,
            "줄거리"
        )
        val reservation = Reservation(movie, 2, LocalDateTime.of(2023, 3, 10, 12, 0))

        val actual = DiscountPolicy.getDiscountedFee(reservation)

        val expected = reservation.initReservationFee - reservation.initReservationFee / 10
        assert(expected == actual)
    }

    @Test
    fun `조조일 때 예매하면 한 사람당 2000원 할인을 받는다`() {
        val movie = Movie(
            "아바타",
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2023, 3, 31),
            Minute(120),
            1,
            "줄거리"
        )
        val peopleCount = 2
        val reservation = Reservation(movie, peopleCount, LocalDateTime.of(2023, 3, 1, 11, 0))

        val actual = DiscountPolicy.getDiscountedFee(reservation)

        val expected = reservation.initReservationFee - Money(peopleCount * 2000)
        assert(expected == actual)
    }

    @Test
    fun `야간일 때 예매하면 한 사람당 2000원 할인을 받는다`() {
        val movie = Movie(
            "아바타",
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2023, 3, 31),
            Minute(120),
            1,
            "줄거리"
        )
        val peopleCount = 2
        val reservation = Reservation(movie, peopleCount, LocalDateTime.of(2023, 3, 1, 20, 0))

        val actual = DiscountPolicy.getDiscountedFee(reservation)

        val expected = reservation.initReservationFee - Money(peopleCount * 2000)
        assert(expected == actual)
    }

    @Test
    fun `무비 데이 할인과 상영 시간 할인을 둘 다 받으면 무비 데이 할인을 먼저 적용된다`() {
        val movie = Movie(
            "아바타",
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2023, 3, 31),
            Minute(120),
            1,
            "줄거리"
        )
        val peopleCount = 2
        val reservation = Reservation(movie, peopleCount, LocalDateTime.of(2023, 3, 10, 20, 0))

        val actual = DiscountPolicy.getDiscountedFee(reservation)

        val expected =
            reservation.initReservationFee - reservation.initReservationFee / 10 - Money(peopleCount * 2000)
        assert(expected == actual)
    }

    @Test
    fun `어떤 할인 조건도 만족하지 않는다면 할인된 금액은 초기 예매 금액과 같다`() {
        val movie = Movie(
            "아바타",
            LocalDate.of(2023, 3, 1),
            LocalDate.of(2023, 3, 31),
            Minute(120),
            1,
            "줄거리"
        )
        val peopleCount = 2
        val reservation = Reservation(movie, peopleCount, LocalDateTime.of(2023, 3, 1, 19, 0))

        val actual = DiscountPolicy.getDiscountedFee(reservation)

        val expected = reservation.initReservationFee
        assert(expected == actual)
    }
}
