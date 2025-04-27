package woowacourse.movie.domain

class Seat(
    val row: Row,
    val column: Column,
) {
    val grade: SeatGrade = row.grade
    val price: Int = row.price
}

fun Seat(
    row: Int,
    column: Int,
): Seat = Seat(Row(row), Column(column))
