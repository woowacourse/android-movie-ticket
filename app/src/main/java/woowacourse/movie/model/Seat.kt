package woowacourse.movie.model

data class Seat(
    val seatName: String,
    val isSelected: Boolean = false,
) {
    val grade: SeatGrade = SeatGrade.fromRow(seatName.first())

    init {
        require(seatName.isNotBlank()) { "좌석 이름은 비어 있을 수 없습니다." }
        require(seatName.first() in listOf('A', 'B', 'C', 'D', 'E')) {
            "지원하지 않는 좌석 행입니다"
        }
    }
}
