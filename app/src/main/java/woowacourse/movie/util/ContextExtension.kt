package woowacourse.movie.util

import android.content.Context
import android.widget.Toast

fun Context.shortToast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
