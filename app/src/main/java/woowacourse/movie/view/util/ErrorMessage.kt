package woowacourse.movie.view.util

@JvmInline
value class ErrorMessage(
    private val cause: String,
) {
    constructor(cause: Int) : this(cause.toString())

    fun notProvided() = "$cause $ERROR_MESSAGE_NO_DATA_FORMAT"

    fun notSelected() = "$cause ${ERROR_MESSAGE_NOT_SELECTED_YET_FORMAT}T"

    fun doesNotExist() = "$cause $ERROR_MESSAGE_DOES_NOT_EXIST"

    companion object {
        private const val ERROR_MESSAGE_NO_DATA_FORMAT = "was not provided."
        private const val ERROR_MESSAGE_NOT_SELECTED_YET_FORMAT = "is not selected yet."
        private const val ERROR_MESSAGE_DOES_NOT_EXIST = "does not exist."
    }
}
