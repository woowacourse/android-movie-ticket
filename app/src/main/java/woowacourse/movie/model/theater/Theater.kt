package woowacourse.movie.model.theater

class Theater private constructor(
    val theaterSize: TheaterSize,
    val rowClassInfo: Map<Int, SeatClass>,
) {
    companion object {
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
        ): Theater =
            Theater(
                theaterSize =
                    TheaterSize(
                        rowClassInfo.size,
                        columnSize,
                    ),
                rowClassInfo = rowClassInfo,
            )
    }
}
