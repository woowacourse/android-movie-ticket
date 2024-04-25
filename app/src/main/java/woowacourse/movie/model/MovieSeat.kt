package woowacourse.movie.model

class MovieSeat(val row: Char, val column: Int) {
    val grade: MovieGrade =
        when (row) {
            in 'A'..'B' -> MovieGrade.B_GRADE
            in 'C'..'D' -> MovieGrade.S_GRADE
            else -> MovieGrade.A_GRADE
        }
}
