package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTest {

    @Test
    fun `좌석이 1행이면 좌석의 등급은 B이다`() {
        // given,when
        val seat = Seat(1, 1)

        // then
        assertThat(seat.grade()).isEqualTo(Grade.B)
    }

    @Test
    fun `좌석이 3행이면 좌석의 등급은 S이다`() {
        // given,when
        val seat = Seat(3, 3)

        // then
        assertThat(seat.grade()).isEqualTo(Grade.S)
    }


    @Test
    fun `좌석이 5행이면 좌석의 등급은 A이다`() {
        // given,when
        val seat = Seat(5, 1)

        // then
        assertThat(seat.grade()).isEqualTo(Grade.A)
    }

    @Test
    fun `좌석의 등급이 B이면 가격은 10_000이다`() {
        // given
        val seat = Seat(1, 1)
        val grade = seat.grade()

        // when
        val price = seat.price(grade)

        // then
        assertThat(price).isEqualTo(10_000)
    }


    @Test
    fun `좌석의 등급이 S이면 가격은 15_000이다`() {
        // given
        val seat = Seat(3, 1)
        val grade = seat.grade()

        // when
        val price = seat.price(grade)

        // then
        assertThat(price).isEqualTo(15_000)
    }


    @Test
    fun `좌석의 등급이 A이면 가격은 12_000이다`() {
        // given
        val seat = Seat(5, 1)
        val grade = seat.grade()

        // when
        val price = seat.price(grade)

        // then
        assertThat(p현rice).isEqualTo(12_000)
    }
}

