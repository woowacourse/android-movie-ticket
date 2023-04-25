package woowacourse.movie.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.shortToast(@StringRes stringId: Int) {
    Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
