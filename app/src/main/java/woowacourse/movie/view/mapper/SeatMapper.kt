package woowacourse.movie.view.mapper

import com.example.domain.Seat

fun Seat.toUi(): String {
    return ('A' + row - 1).toString() + "$column"
}
