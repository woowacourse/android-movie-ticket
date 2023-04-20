package woowacourse.movie.ui.seatreservation.domain

enum class Seat(private val index: Int, val rank: String, val price: Int) {
    A1(0, "B등급", 10000),
    A2(1, "B등급", 10000),
    A3(2, "B등급", 10000),
    A4(3, "B등급", 10000),
    B1(4, "B등급", 10000),
    B2(5, "B등급", 10000),
    B3(6, "B등급", 10000),
    B4(7, "B등급", 10000),
    C1(8, "S등급", 15000),
    C2(9, "S등급", 15000),
    C3(10, "S등급", 15000),
    C4(11, "S등급", 15000),
    D1(12, "S등급", 15000),
    D2(13, "S등급", 15000),
    D3(14, "S등급", 15000),
    D4(15, "S등급", 15000),
    E1(16, "A등급", 12000),
    E2(17, "A등급", 12000),
    E3(18, "A등급", 12000),
    E4(19, "A등급", 12000),
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
