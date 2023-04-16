package woowacourse.movie.domain

import woowacourse.movie.domain.discountPolicy.Discount
import woowacourse.movie.domain.discountPolicy.MovieDay
import woowacourse.movie.domain.discountPolicy.OffTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieReservationOffice {
    fun makeReservationDetail(
        screenDate: LocalDate,
        screenTime: LocalTime,
        peopleCount: Int,
    ): ReservationDetail = ReservationDetail(
        LocalDateTime.of(
            screenDate,
            screenTime
        ),
        peopleCount,
        Discount(listOf(MovieDay(), OffTime()))
    )

    fun makeReservation(movie: Movie, reservationDetail: ReservationDetail): Reservation =
        Reservation(movie, reservationDetail)
}
