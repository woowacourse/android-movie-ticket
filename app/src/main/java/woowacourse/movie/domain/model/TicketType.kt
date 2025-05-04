package woowacourse.movie.domain.model

enum class TicketType(
    val price: Int,
) {
    S_GRADE(15000),
    A_GRADE(12000),
    B_GRADE(10000),
    ;

    companion object {
        fun ticketTypeByRow(row: Int): TicketType =
            when {
                row < 2 -> B_GRADE
                row < 4 -> S_GRADE
                row < 5 -> A_GRADE
                else -> B_GRADE
            }
    }
}
