package woowacourse.movie.model

data class Seat(
    val seatName: String,
    val grade: SeatGrade,
    val isSelected: Boolean = false,
) {
    init {
        require(seatName.first() in listOf('A', 'B', 'C', 'D', 'E')) {
            "지원하지 않는 좌석 행입니다"
        }
        require(grade in SeatGrade.entries.toTypedArray()) {
            "지원하지 않는 등급입니다"
        }
    }
}
