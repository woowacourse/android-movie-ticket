package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TicketTimeTest() {
    @Test
    fun `10일은 무비데이 이다`() {
        assertThat(TicketTime(LocalDateTime.of(2023, 4, 10, 15, 0)).isMovieDay()).isTrue
    }

    @Test
    fun `20일은 무비데이 이다`() {
        assertThat(TicketTime(LocalDateTime.of(2023, 4, 20, 15, 0)).isMovieDay()).isTrue
    }

    @Test
    fun `30일은 무비데이 이다`() {
        assertThat(TicketTime(LocalDateTime.of(2023, 4, 30, 15, 0)).isMovieDay()).isTrue
    }

    @Test
    fun `14일은 무비데이가 아니다`() {
        assertThat(TicketTime(LocalDateTime.of(2023, 4, 14, 15, 0)).isMovieDay()).isFalse
    }

    @Test
    fun `11시는 영화가 할인되는 시간이다`() {
        assertThat(TicketTime(LocalDateTime.of(2023, 4, 14, 11, 0)).isSaleTime()).isTrue
    }

    @Test
    fun `20시는 영화가 할인되는 시간이다`() {
        assertThat(TicketTime(LocalDateTime.of(2023, 4, 14, 20, 0)).isSaleTime()).isTrue
    }

    @Test
    fun `12시는 영화가 할인되는 시간이 아니다`() {
        assertThat(TicketTime(LocalDateTime.of(2023, 4, 14, 12, 0)).isSaleTime()).isFalse
    }

    @Test
    fun `19시는 영화가 할인되는 시간이다`() {
        assertThat(TicketTime(LocalDateTime.of(2023, 4, 14, 19, 0)).isSaleTime()).isFalse
    }
}
