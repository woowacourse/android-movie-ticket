package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieTheaterTest {
    @Test
    fun `상영관의 1행은 S등급, 2행은 B등급, 열의 길이가 2일 때 그에 따른 좌석들을 생성한다`() {
        val theater = MovieTheater(mapOf(SeatRate.S to listOf(1), SeatRate.A to listOf(2)), 2)
        val seats = theater.seats()
        assertThat(seats).contains(
            Seat(SeatRate.S, 0, 0),
            Seat(SeatRate.S, 0, 1),
            Seat(SeatRate.A, 1, 0),
            Seat(SeatRate.A, 1, 1),
        )
    }
}
