package domain

enum class Grade(val ticketPrice: TicketPrice) {
    B(TicketPrice(10_000)),
    S(TicketPrice(15_000)),
    A(TicketPrice(12_000)),
    ;

    companion object {
        fun checkGrade(row: Int): Grade {
            return when (row) {
                1, 2 -> B
                3, 4 -> S
                else -> A
            }
        }
    }
}
