package woowacourse.movie.model

sealed class Result<T>(open val data: T?, open val message: String?) {
    data class Success<T>(override val data: T) : Result<T>(data, null)

    data class Error<T>(override val message: String) : Result<T>(null, message)
}
