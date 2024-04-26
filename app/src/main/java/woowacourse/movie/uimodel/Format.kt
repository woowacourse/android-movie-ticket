package woowacourse.movie.uimodel

import woowacourse.movie.model.ScreeningDate
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.RunningTime
import woowacourse.movie.model.movie.Synopsis
import woowacourse.movie.model.ScreeningPeriod
import woowacourse.movie.model.movie.Title

fun Movie.toBrief() = MovieBrief(
    title = this.movieDetail.title.content,
    screeningDate = "상영일: ${this.screeningPeriod.format()}",
    runningTime = "러닝타임: ${this.movieDetail.runningTime.format()}" + "분"
)

fun Title.format() = content

fun ScreeningPeriod.format() =
    start.format() + "~" + end.format()

fun ScreeningDate.format() =
    "${date.year}.${date.monthValue}.${date.dayOfMonth}"

fun RunningTime.format() = time.toString()

fun Synopsis.format() = content
