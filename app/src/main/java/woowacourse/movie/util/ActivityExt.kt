package woowacourse.movie.util

import android.app.Activity
import android.util.Log
import woowacourse.movie.R

fun Activity.keyError(key: String) {
    Log.d("mendel", getString(R.string.no_key_exist_error, key))
    finish()
}
