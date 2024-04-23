package woowacourse.movie.domain.model

const val POSTER_IMAGE_ID = 1
const val TITLE = "테넷"
const val SCREENING_DATE = "2021-08-30"
const val RUNNING_TIME = 150
const val SUMMARY = "시간을 건너뛰는 SF 영화"

fun createMovieInfo(): Movie =
    Movie(
        posterImageId = POSTER_IMAGE_ID,
        title = TITLE,
        screeningDate = SCREENING_DATE,
        runningTime = RUNNING_TIME,
        summary = SUMMARY,
    )
