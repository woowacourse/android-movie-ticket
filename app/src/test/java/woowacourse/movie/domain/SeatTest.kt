package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SeatTest {
    @Test
    fun `A열은 10,000원이다`() {
        //given
        val expected = 10_000

        //when
        val actual = Seat("A1").price()

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `B열은 10,000원이다`() {
        //given
        val expected = 10_000

        //when
        val actual = Seat("B1").price()

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `C열은 15,000원이다`() {
        //given
        val expected = 15_000

        //when
        val actual = Seat("C1").price()

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `D열은 15,000원이다`() {
        //given
        val expected = 15_000

        //when
        val actual = Seat("D1").price()

        //then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `E열은 12,000원이다`() {
        //given
        val expected = 12_000

        //when
        val actual = Seat("E1").price()

        //then
        assertThat(actual).isEqualTo(expected)
    }
}