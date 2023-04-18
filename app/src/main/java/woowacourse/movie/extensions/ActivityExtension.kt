package woowacourse.movie.extensions

import android.app.Activity

internal fun Activity.exitForUnNormalCase(message: String) {
    showToast(message)
    this.finish()
}
