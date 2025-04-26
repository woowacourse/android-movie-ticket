package woowacourse.movie.model

enum class SeatGrade(val price: Int) {
    B(10000),
    S(15000),
    A(12000),
    ;

    companion object {
        fun fromRow(rowChar: Char): SeatGrade {
            return when (rowChar.uppercaseChar()) {
                'A', 'B' -> B
                'C', 'D' -> S
                'E' -> A
                else -> throw IllegalArgumentException("지원하지 않는 행입니다")
            }
        }
    }
}
