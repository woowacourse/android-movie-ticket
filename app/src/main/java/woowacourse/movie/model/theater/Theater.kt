package woowacourse.movie.model.theater

class Theater private constructor(
    val theaterSize: TheaterSize,
    val sizesOfRows: Map<SeatClass, Int>,
) {
    companion object {
        private const val DEFAULT_COLUMN_SIZE = 4
        private const val DEFAULT_S_ROWS_SIZE = 2
        private const val DEFAULT_A_ROWS_SIZE = 1
        private const val DEFAULT_B_ROWS_SIZE = 2

        fun of(
            sRowsSize: Int = DEFAULT_S_ROWS_SIZE,
            aRowsSize: Int = DEFAULT_A_ROWS_SIZE,
            bRowsSize: Int = DEFAULT_B_ROWS_SIZE,
            columnSize: Int = DEFAULT_COLUMN_SIZE,
        ): Theater =
            Theater(
                theaterSize =
                    TheaterSize(
                        sRowsSize + aRowsSize + bRowsSize,
                        columnSize,
                    ),
                sizesOfRows =
                    mapOf(
                        SeatClass.S to sRowsSize,
                        SeatClass.A to aRowsSize,
                        SeatClass.B to bRowsSize,
                    ),
            )
    }
}
