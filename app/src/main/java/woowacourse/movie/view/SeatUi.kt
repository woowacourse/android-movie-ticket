package woowacourse.movie.view

import com.example.domain.Seat

fun Seat.toUi(): String {
    return ('A' + row - 1).toString() + "$column"
}
