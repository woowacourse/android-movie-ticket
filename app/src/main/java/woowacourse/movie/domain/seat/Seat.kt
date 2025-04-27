package woowacourse.movie.domain.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat(
    val row: Row,
    val col: Col,
    val grade: SeatGrade,
) : Parcelable {
    fun price(): Int = grade.price


    companion object {
        fun of(row: Int, col: Int) : Seat {
            val grade = gradeByRow(row)
            return Seat(Row(row), Col(col), grade)
        }

        private fun gradeByRow(row: Int): SeatGrade {
            return when (row) {
                0, 1 -> SeatGrade.B
                2, 3 -> SeatGrade.S
                4 -> SeatGrade.A
                else -> throw IllegalArgumentException("지원하지 않는 행입니다: $row")
            }
        }
    }
}
