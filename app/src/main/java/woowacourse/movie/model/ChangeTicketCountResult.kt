package woowacourse.movie.model

sealed interface ChangeTicketCountResult

data object Success : ChangeTicketCountResult

data object Failure : ChangeTicketCountResult
