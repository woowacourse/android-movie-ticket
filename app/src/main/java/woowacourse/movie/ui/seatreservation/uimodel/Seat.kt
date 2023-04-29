package woowacourse.movie.ui.seatreservation.uimodel

enum class Seat(private val index: Int, val price: Int) {
    A1(0, 10000),
    A2(1, 10000),
    A3(2, 10000),
    A4(3, 10000),
    B1(4, 10000),
    B2(5, 10000),
    B3(6, 10000),
    B4(7, 10000),
    C1(8, 15000),
    C2(9, 15000),
    C3(10, 15000),
    C4(11, 15000),
    D1(12, 15000),
    D2(13, 15000),
    D3(14, 15000),
    D4(15, 15000),
    E1(16, 12000),
    E2(17, 12000),
    E3(18, 12000),
    E4(19, 12000),
    ;

    private fun matchSeatGrade(index: Int) =
        this.index == index

    companion object {
        private const val ERROR_ABNORMAL = "[ERROR] 비정상적인 접근입니다."
        fun valueOf(index: Int): Seat =
            values().find { seat ->
                seat.matchSeatGrade(index)
            } ?: throw IllegalStateException(ERROR_ABNORMAL)
    }
}
