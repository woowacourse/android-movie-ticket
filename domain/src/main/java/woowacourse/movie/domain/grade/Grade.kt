package woowacourse.movie.domain.grade

enum class Grade(val price: Int, val rowIndexRange: IntRange) {
    S(15000, 2..3),
    B(10000, 0..1),
    A(12000, 4..4);

    companion object {
        private const val OUT_RANGE_ROW_INDEX = "들어온 row Index값이 정해진 row값 범위내에 없습니다."
        fun getGrade(rowIndex: Int): Grade =
            Grade.values().find { rowIndex in it.rowIndexRange } ?: throw IllegalStateException(
                OUT_RANGE_ROW_INDEX
            )
    }
}
