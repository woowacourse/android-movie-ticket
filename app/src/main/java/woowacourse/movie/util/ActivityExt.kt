package woowacourse.movie.util

import android.app.Activity
import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.R

fun Activity.keyError(key: String) {
    Log.d("mendel", getString(R.string.no_key_exist_error, key))
    finish()
}

fun Activity.showAskDialog(
    @StringRes
    titleId: Int,
    @StringRes
    messageId: Int,
    @StringRes
    negativeStringId: Int,
    @StringRes
    positiveStringId: Int,
    actionNegative: () -> Unit = {},
    actionPositive: () -> Unit = {}
) {
    AlertDialog.Builder(this)
        .setTitle(getString(titleId))
        .setMessage(getString(messageId))
        .setNegativeButton(negativeStringId) { _, _ ->
            actionNegative()
        }.setPositiveButton(positiveStringId) { _, _ ->
            actionPositive()
        }.setCancelable(false)
        .show()
}
