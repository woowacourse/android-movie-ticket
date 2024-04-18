package woowacourse.movie.model

sealed interface Result

data object Success : Result

data object Failure : Result
