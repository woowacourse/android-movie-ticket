package domain

sealed interface Result

data object Success : Result

data object Failure : Result
