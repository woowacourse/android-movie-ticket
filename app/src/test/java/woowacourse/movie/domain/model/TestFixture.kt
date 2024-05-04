package woowacourse.movie.domain.model

import java.time.LocalDate

const val MOVIE_ID = 0
const val POSTER_IMAGE_ID = 1
const val TITLE = "테넷"
const val SCREENING_DATE = "2021-08-30"
const val RUNNING_TIME = 150
const val SUMMARY = "시간을 건너뛰는 SF 영화"
val screeningInfo =
    ScreeningInfo(
        startDate = LocalDate.of(2024, 4, 1),
        endDate = LocalDate.of(2024, 4, 30),
        runningTime = 148,
    )

fun createMovieInfo(): Movie =
    Movie(
        movieId = MOVIE_ID,
        posterImageId = POSTER_IMAGE_ID,
        title = TITLE,
        screeningInfo = screeningInfo,
        summary = SUMMARY,
    )
