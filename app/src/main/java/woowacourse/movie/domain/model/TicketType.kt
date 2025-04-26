package woowacourse.movie.domain.model

enum class TicketType(
    val price: Int,
) {
    S_GRADE(15000),
    A_GRADE(12000),
    B_GRADE(10000),
}
