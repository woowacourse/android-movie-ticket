package woowacourse.movie.view.model

import com.example.domain.Seat

fun Seat.toUi(): String {
    return ('A' + row - 1).toString() + "$column"
}
