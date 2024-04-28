package woowacourse.movie.model

import java.io.Serializable

data class Seat(
    val row: Int,
    val col: Int,
) : Serializable {
    fun grade(): Grade {
        return when (row) {
            in ROW_RANGE_GRADE_B -> Grade.B
            in ROW_RANGE_GRADE_S -> Grade.S
            else -> Grade.A
        }
    }

    fun price(grade: Grade): Int = grade.price

    companion object {
        val ROW_RANGE_GRADE_B = 0..1
        val ROW_RANGE_GRADE_S = 2..3
    }
}
