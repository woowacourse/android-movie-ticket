package woowacourse.movie

import android.content.Context

data class DialogInfo(
    val context: Context,
    val title: String,
    val message: String,
    val positiveMessage: String?,
    val negativeMessage: String?,
) {
    constructor(
        context: Context,
        title: Int,
        message: Int,
        positiveMessage: Int?,
        negativeMessage: Int?,
    ) : this(
        context,
        context.getString(title),
        context.getString(message),
        positiveMessage?.let { context.getString(it) },
        negativeMessage?.let { context.getString(it) },
    )
}
