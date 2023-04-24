package woowacourse.movie.view.error

sealed interface ViewError {
    val message: String

    class ActivityMissingExtras(override val message: String) : ViewError
}
