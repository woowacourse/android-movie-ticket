package woowacourse.movie.domain

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class TicketTimeTest() {
    @Test
    fun `10일은 무비데이 이다`() {
        assertTrue(TicketTime(LocalDate.of(2023, 4, 10), Time(15)).isMovieDay())
    }

    @Test
    fun `20일은 무비데이 이다`() {
        assertTrue(TicketTime(LocalDate.of(2023, 4, 20), Time(15)).isMovieDay())
    }

    @Test
    fun `30일은 무비데이 이다`() {
        assertTrue(TicketTime(LocalDate.of(2023, 4, 30), Time(15)).isMovieDay())
    }

    @Test
    fun `14일은 무비데이가 아니다`() {
        assertFalse(TicketTime(LocalDate.of(2023, 4, 14), Time(15)).isMovieDay())
    }

    @Test
    fun `11시는 영화가 할인되는 시간이다`() {
        assertTrue(TicketTime(LocalDate.of(2023, 4, 14), Time(11)).isSaleTime())
    }

    @Test
    fun `20시는 영화가 할인되는 시간이다`() {
        assertTrue(TicketTime(LocalDate.of(2023, 4, 14), Time(20)).isSaleTime())
    }

    @Test
    fun `12시는 영화가 할인되는 시간이 아니다`() {
        assertFalse(TicketTime(LocalDate.of(2023, 4, 14), Time(12)).isSaleTime())
    }

    @Test
    fun `19시는 영화가 할인되는 시간이다`() {
        assertFalse(TicketTime(LocalDate.of(2023, 4, 14), Time(19)).isSaleTime())
    }
}
