package woowacourse.movie.factory

data class DialogInfo(
    val title: String,
    val message: String,
    val positiveMessage: String,
    val negativeMessage: String?,
    val onConfirm: () -> Unit,
    val onDismiss: () -> Unit = {},
)
