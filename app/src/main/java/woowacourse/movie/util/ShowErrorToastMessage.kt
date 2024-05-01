package woowacourse.movie.util

import android.content.Context
import android.widget.Toast

fun showErrorToastMessage(
    context: Context,
    messageRes: String,
) {
    Toast.makeText(context, messageRes, Toast.LENGTH_SHORT).show()
}
