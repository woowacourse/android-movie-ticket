package woowacourse.movie.utils

import android.app.Activity
import android.widget.Toast

fun Activity.keyError(key: String) {
    Toast.makeText(this, "Key '$key' is not found in bundle", Toast.LENGTH_LONG).show()
    finish()
}
