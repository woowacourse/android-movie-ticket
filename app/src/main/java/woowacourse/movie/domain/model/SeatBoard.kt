package woowacourse.movie.domain.model

data class SeatBoard(
    val id: Int,
    val horizontalLength: Int,
    val verticalLength: Int,
    val seats: List<Seat>,
)
