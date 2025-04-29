package woowacourse.movie.model

@JvmInline
value class Seats(
    val values: List<Seat>,
) {
    init {
        require(values.all { it.isSelected }) { "선택되지 않은 Seat은 포함될 수 없습니다" }
    }

    val amount: Int
        get() = values.sumOf { it.grade.price }

    fun toggle(
        seat: Seat,
        headCount: Int,
    ): Seats {
        val existingSeat = values.find { it.seatName == seat.seatName }

        return if (existingSeat != null) {
            Seats(
                values.map {
                    if (it.seatName == seat.seatName) it.copy(isSelected = false) else it
                }.filter { it.isSelected },
            )
        } else {
            if (values.count { it.isSelected } >= headCount) {
                this
            } else {
                Seats(values + seat.copy(isSelected = true))
            }
        }
    }
}
