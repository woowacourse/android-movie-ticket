package woowacourse.movie.view.util

@JvmInline
value class ErrorMessage(
    private val cause: String,
) {
    fun notProvided() = "$cause $ERROR_MESSAGE_NO_DATA_FORMAT"

    fun notSelected() = "$cause ${ERROR_MESSAGE_NOT_SELECTED_YET_FORMAT}T"

    fun noSuch(`in`: Any) = "no such $cause in $`in`"

    companion object {
        private const val ERROR_MESSAGE_NO_DATA_FORMAT = "was not provided."
        private const val ERROR_MESSAGE_NOT_SELECTED_YET_FORMAT = "is not selected yet."
    }
}
