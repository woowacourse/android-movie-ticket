package woowacourse.movie.uimodel.reservation

import woowacourse.movie.model.schedule.ScreeningDateTime
import woowacourse.movie.model.seat.Position

internal fun Position.format(): String {
    val row: Char = ((this.y - 1) + 'A'.code).toChar()
    val col = this.x
    return "$row$col"
}

internal fun ScreeningDateTime.format(): String {
    val dateTime = this.dateTime
    return "${dateTime.monthValue}월 ${dateTime.dayOfMonth}일 ${dateTime.hour}시"
}
