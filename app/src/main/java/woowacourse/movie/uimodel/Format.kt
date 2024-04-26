package woowacourse.movie.uimodel

import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.RunningTime
import woowacourse.movie.model.movie.Synopsis
import woowacourse.movie.model.movie.ScreeningDate
import woowacourse.movie.model.movie.Title

fun Movie.toBrief() = MovieBrief(
    title = this.movieDetail.title.content,
    screeningDate = "상영일: ${this.screeningDate.format()}",
    runningTime = "러닝타임: ${this.movieDetail.runningTime.format()}" + "분"
)

fun Title.format() = content

fun ScreeningDate.format() = "${date.year}.${date.monthValue}.${date.dayOfMonth}"

fun RunningTime.format() = time.toString()

fun Synopsis.format() = content
