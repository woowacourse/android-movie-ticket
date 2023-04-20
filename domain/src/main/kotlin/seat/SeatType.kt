package seat

enum class SeatType(val paymentAmount: Int, val targetRows: List<Char>) {
    S(15_000, listOf('C', 'D')),
    A(13_000, listOf('E')),
    B(10_000, listOf('A', 'B'))
}
