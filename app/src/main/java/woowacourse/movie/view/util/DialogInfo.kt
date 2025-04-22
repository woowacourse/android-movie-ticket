package woowacourse.movie.view.util

import android.content.DialogInterface

class DialogInfo(
    val title: String,
    val message: String,
    val isCancelable: Boolean = false,
    val negativeButtonText: String? = null,
    val positiveButtonText: String? = null,
    val onClickNegativeButton: (DialogInterface) -> Unit = {},
    val onClickPositiveButton: (DialogInterface) -> Unit = {},
)
