import woowacourse.movie.domain.SeatRow
import java.io.Serializable

fun indexToPosition(index: Int): SeatPosition {
    val column = index % 4
    val row = index / 4
    return SeatPosition(
        row = SeatRow.of(row),
        column = column + 1
    )
}

class SeatPosition(private val row: SeatRow, private val column: Int) : Serializable {
    override fun toString(): String {
        return "${row.name}$column"
    }
}
