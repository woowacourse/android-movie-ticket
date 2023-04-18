package woowacourse.movie.domain

import com.example.domain.Minute
import com.example.domain.Movie
import com.example.domain.ReservationAgency
import com.example.domain.Seat
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime

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
    fun `선택한 좌석은 2개 예매 인원은 3명이면 예매할 수 없다`(){
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
