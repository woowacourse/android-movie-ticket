package movie.domain.seat

enum class RowSeat(val row: Int) {
    A(0), B(1), C(2), D(3), E(4);

    companion object {
        fun valueOf(index: Int): RowSeat {
            return RowSeat.values().find {
                it.row == index
            } ?: throw IllegalArgumentException("해당하는 값이 없습니다.")
        }
    }
}
