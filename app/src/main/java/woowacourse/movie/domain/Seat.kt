package woowacourse.movie.domain

import java.io.Serializable

data class Seat(
    val x: Int,
    val y: Int,
) : Serializable {
    fun price(): Int {
        return when (x) {
            in B_CLASS_ROW -> B_CLASS_PRICE
            in S_CLASS_ROW -> S_CLASS_PRICE
            else -> A_CLASS_PRICE
        }
    }

    companion object {
        private val B_CLASS_ROW = setOf(0, 1)
        private val S_CLASS_ROW = setOf(2, 3)

        private const val B_CLASS_PRICE = 10_000
        private const val A_CLASS_PRICE = 12_000
        private const val S_CLASS_PRICE = 15_000
    }
}
