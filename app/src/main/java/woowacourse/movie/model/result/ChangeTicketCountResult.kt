package woowacourse.movie.model.result

sealed interface ChangeTicketCountResult

data object Success : ChangeTicketCountResult

data object Failure : ChangeTicketCountResult
