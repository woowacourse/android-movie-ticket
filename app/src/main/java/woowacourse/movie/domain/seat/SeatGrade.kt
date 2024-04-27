package woowacourse.movie.domain.seat

enum class SeatGrade(val price: Long) {
    B(10000),
    A(12000),
    S(15000),
    ;

    companion object {
        fun getGrade(row: String): SeatGrade {
            return when (row) {
                "A", "B" -> B
                "C", "D" -> S
                else -> A
            }
        }
    }
}
