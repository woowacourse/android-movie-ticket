package woowacourse.movie.model.theater

import java.io.Serializable

data class Seat(
    val row: Char,
    val number: Int,
    val grade: String
) : Serializable {

    val price: Int
    val color: Int
    var chosen: Boolean = false

    companion object {
        val seatGrades = mapOf(
            "B" to Pair(10000, 0x800080),
            "S" to Pair(15000, 0x008000),
            "A" to Pair(12000, 0x0000FF),
        )
    }

    init {
        val gradeInfo = seatGrades[grade] ?: throw IllegalArgumentException("Invalid seat grade")
        price = gradeInfo.first
        color = gradeInfo.second
    }

    fun chooseSeat() {
        chosen=!chosen
    }
}
