package woowacourse.movie.domain.model.seat

enum class SeatGrade(val price: Int) {
    B(10_000),
    A(12_000),
    S(15_000);

    companion object {
        fun from(colIndex: Int): SeatGrade {
            return when(colIndex) {
                0, 1 -> B
                2, 3 -> S
                4 -> A
                else -> throw IllegalArgumentException(GRADE_ERROR)
            }
        }

        private const val GRADE_ERROR = "[ERROR] 해당하는 좌석 등급이 존재하지 않습니다."
    }
}