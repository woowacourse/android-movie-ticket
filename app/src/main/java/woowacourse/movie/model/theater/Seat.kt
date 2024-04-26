package woowacourse.movie.model.theater

import android.graphics.Color
import java.io.Serializable

data class Seat(
    val row: Char,
    val number: Int,
    val grade: String
):Serializable {
    var price: Int
    var color: Int

    companion object {
        val seatGrades = mapOf(
            "B" to Pair(10000, Color.parseColor("#800080")),
            "S" to Pair(15000, Color.parseColor("#008000")),
            "A" to Pair(12000, Color.parseColor("#0000FF"))
        )
    }

    init {
        val gradeInfo = seatGrades[grade] ?: throw IllegalArgumentException("Invalid seat grade")
        price = gradeInfo.first
        color = gradeInfo.second
    }
}
