package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieSeat
import woowacourse.movie.domain.ScreenDateTime
import woowacourse.movie.domain.Tier
import woowacourse.movie.presentation.adapter.viewtype.MovieItemViewType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val MOVIE =
    Movie(
        id = 0,
        thumbnailUrl =
            "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver" +
                ".net%2FMjAyNDAxMjhfMzcg%2FMDAxNzA2NDE0ODEzMjE4.2VzXfZSC-elDIh7-R1u_P0coH9msycrnfPo2X-" +
                "YD8REg.sfAS4C6YTCwty1Yox1zsEv3A-Z4Vvpr-xvNSftYCZMcg.JPEG.blue_rm%2F18.jpg&type=a340",
        title = "타이타닉",
        description =
            "우연한 기회로 티켓을 구해 타이타닉호에 올라탄 자유로운 영혼을 가진 화가 ‘잭’(레오나르도 디카프리오)은 막강한 재력의 약혼자와 함께 1등실에 승선한 ‘로즈’(케이트 윈슬렛)에게 한눈에 반한다. " +
                "진실한 사랑을 꿈꾸던 ‘로즈’ 또한 생애 처음 황홀한 감정에 휩싸이고, 둘은 운명 같은 사랑에 빠지는데… 가장 차가운 곳에서 피어난 뜨거운 사랑! 영원히 가라앉지 않는 세기의 사랑이 펼쳐진다!",
        screenDateTime =
            mutableListOf(
                ScreenDateTime(
                    id = 0,
                    dateTime = LocalDateTime.of(LocalDate.of(2024, 4, 1), LocalTime.of(10, 0)),
                ),
            ),
        runningTime = 152,
    )

val MOVIE_ITEM =
    MovieItemViewType.MovieView(
        movie = MOVIE,
    )

val SCREEN_DATE_TIME =
    ScreenDateTime(
        id = 0,
        dateTime = LocalDateTime.of(LocalDate.of(2024, 4, 1), LocalTime.of(10, 0)),
    )

val A1_SEAT = MovieSeat(id = 0, movieSeatBoardId = 0, number = 0, tier = Tier.B)
val C1_SEAT = MovieSeat(id = 8, movieSeatBoardId = 0, number = 8, tier = Tier.A)
val E1_SEAT = MovieSeat(id = 16, movieSeatBoardId = 0, number = 16, tier = Tier.S)

val SEAT =
    E1_SEAT

val SEATS =
    listOf(
        A1_SEAT,
        C1_SEAT,
        E1_SEAT,
    )
