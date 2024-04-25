package woowacourse.movie.presentation.utils

import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.SeatRank
import woowacourse.movie.presentation.model.ReservationInfo
import java.time.LocalDateTime

fun getDummyMovie(): Movie {
    return Movie(
        title = "해리 포터와 마법사의 돌",
        runningTime = 152,
        imageSrc = R.drawable.img_poster,
        description =
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
        13_000,
    )
}

fun getDummySeats(): List<Seat> {
    return listOf(
        Seat("A", 1, SeatRank.A),
        Seat("A", 2, SeatRank.A),
        Seat("A", 3, SeatRank.A),
    )
}

fun getDummyReservation(): Reservation {
    return Reservation(
        id = 1,
        getDummyMovie(),
        3,
        seats = getDummySeats(),
        dateTime = LocalDateTime.now(),
    )
}

fun getDummyReservationInfo(): ReservationInfo {
    return ReservationInfo(1, LocalDateTime.now(), 4)
}
