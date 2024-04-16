package domain

sealed interface Result

data object Success : Result

data object MinTicketsBounds : Result

data object MaxTicketsBounds : Result
