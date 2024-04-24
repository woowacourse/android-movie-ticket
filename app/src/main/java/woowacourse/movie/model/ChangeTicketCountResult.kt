package woowacourse.movie.model

sealed interface ChangeTicketCountResult

data object InRange : ChangeTicketCountResult

data object OutOfRange : ChangeTicketCountResult
