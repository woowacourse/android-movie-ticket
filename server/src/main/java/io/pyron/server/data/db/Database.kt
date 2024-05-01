package io.pyron.server.data.db

import io.pyron.server.data.entity.Movie
import io.pyron.server.data.entity.MovieScreenDateTime
import io.pyron.server.data.entity.MovieSeat
import io.pyron.server.data.entity.MovieSeatBoard
import io.pyron.server.data.entity.ScreenDateTime
import io.pyron.server.data.entity.Tier
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

// 영화 샘플
var dbMovies = generateMovieSample()

// 상영 시간 샘플
var dbScreenDateTimes = generateScreenDateTimeSample()

// 영화 - 상영시간 샘플
var dbMovieScreenDateTimes = generateMovieScreenDateTimeSample()

// 상영관 샘플
var dbMovieSeatBoards = generateSeatBoardSample()

// 좌석 샘플
var dbSeats = generateSeatSample()

fun generateMovieSample(): List<Movie> {
    return mutableListOf<Movie>(
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
            runningTime = 152,
        ),
    )
}

fun generateScreenDateTimeSample(): List<ScreenDateTime> {
    val screenDateTimes = mutableListOf<ScreenDateTime>()
    val startDate = LocalDate.of(2024, 4, 1)
    val endDate = LocalDate.of(2024, 4, 30)
    var currentDate = startDate
    var id: Long = 0
    while (!currentDate.isAfter(endDate)) {
        generateLocalDateTimeSample(currentDate).forEach {
            screenDateTimes.add(ScreenDateTime(id++, it))
        }
        currentDate = currentDate.plusDays(1)
    }
    return screenDateTimes
}

fun generateSeatBoardSample(): List<MovieSeatBoard> {
    val movieSeatBoards = mutableListOf<MovieSeatBoard>()
    var id: Long = 0

    for (index in 0 until 30 * 8) {
        movieSeatBoards.add(
            MovieSeatBoard(
                id = id++,
                movieScreenDateTimeId = index.toLong(),
            ),
        )
    }
    return movieSeatBoards
}

fun generateMovieScreenDateTimeSample(): List<MovieScreenDateTime> {
    val movieScreenDateTimes = mutableListOf<MovieScreenDateTime>()

    for (movieIndex in 0 until 2500) {
        for (index in 0 until 30 * 8) {
            movieScreenDateTimes.add(MovieScreenDateTime(index.toLong(), movieIndex.toLong(), index.toLong()))
        }
    }
    return movieScreenDateTimes
}

fun generateSeatSample(): List<MovieSeat> {
    val movieSeats = mutableListOf<MovieSeat>()
    val rankBThreshold = 8
    val rankAThreshold = 16
    for (movieSeatBoardId in 0 until 30 * 8) {
        for (index in 0 until 20) {
            movieSeats.add(
                MovieSeat(
                    id = ((20 * movieSeatBoardId) + index).toLong(),
                    movieSeatBoardId = movieSeatBoardId.toLong(),
                    number = index,
                    tier =
                        if (index < rankBThreshold) {
                            Tier.B
                        } else if (index < rankAThreshold) {
                            Tier.A
                        } else {
                            Tier.S
                        },
                ),
            )
        }
    }
    return movieSeats
}

private fun generateLocalDateTimeSample(date: LocalDate): List<LocalDateTime> {
    val dateTimes = mutableListOf<LocalDateTime>()

    when (date.dayOfWeek) {
        DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> {
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(9, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(11, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(13, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(15, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(17, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(19, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(21, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(23, 0)))
        }
        else -> {
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(10, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(12, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(14, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(16, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(18, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(20, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(22, 0)))
            dateTimes.add(LocalDateTime.of(date, LocalTime.of(0, 0)))
        }
    }
    return dateTimes
}
