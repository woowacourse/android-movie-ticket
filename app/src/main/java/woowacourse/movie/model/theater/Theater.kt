package woowacourse.movie.model.theater

class Theater private constructor(
    val theaterSize: TheaterSize,
    val rowClassInfo: Map<Int, SeatClass>,
) {
    companion object {
        private const val ERROR_DUPLICATED_ROW_NUMBERS = "중복된 행 번호를 가질 수 없습니다."
        private const val ERROR_ROW_NUMBERS_NOT_IN_A_ROW = "연속된 행 번호를 가져야 합니다."
        private const val DEFAULT_COLUMN_SIZE = 4
        private val DEFAULT_ROW_CLASS_INFO =
            mapOf(
                1 to SeatClass.B,
                2 to SeatClass.B,
                3 to SeatClass.S,
                4 to SeatClass.S,
                5 to SeatClass.A,
            )

        fun of(
            rowClassInfo: Map<Int, SeatClass> = DEFAULT_ROW_CLASS_INFO,
            columnSize: Int = DEFAULT_COLUMN_SIZE,
        ): Theater {
            require(rowClassInfo.keys.distinct().size == rowClassInfo.keys.size) { ERROR_DUPLICATED_ROW_NUMBERS }
            require(rowClassInfo.keys.maxOf { it } == rowClassInfo.keys.size) { ERROR_ROW_NUMBERS_NOT_IN_A_ROW }
            return Theater(
                theaterSize =
                    TheaterSize(
                        rowClassInfo.size,
                        columnSize,
                    ),
                rowClassInfo = rowClassInfo,
            )
        }
    }
}
