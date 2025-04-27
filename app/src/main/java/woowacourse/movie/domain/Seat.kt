package woowacourse.movie.domain

import java.io.Serializable

data class Seat(
    val x: Int,
    val y: Int,
) : Serializable {
    fun price(): Int {
        return when (x) {
            0, 1 -> 10000
            2, 3 -> 12000
            else -> 15000
        }
    }
}
