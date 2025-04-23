package woowacourse.movie.domain

sealed class TicketCountResult {
    data class Success(
        val ticketCount: TicketCount,
    ) : TicketCountResult()

    data class Error(
        val errorMessage: String,
    ) : TicketCountResult()
}

fun TicketCountResult.getOrThrow(): TicketCount =
    when (this) {
        is TicketCountResult.Success -> ticketCount
        is TicketCountResult.Error -> throw IllegalStateException(errorMessage)
    }

fun TicketCountResult.getOrDefault(): TicketCount =
    when (this) {
        is TicketCountResult.Success -> ticketCount
        is TicketCountResult.Error -> TicketCount.createDefault()
    }
