package woowacourse.movie.domain.seat

enum class SeatType(val paymentAmount: Int, val targetRows: List<Row>) {
    S(15_000, listOf(Row('C'), Row('D'))),
    A(12_000, listOf(Row('E'))),
    B(10_000, listOf(Row('A'), Row('B')))
}
