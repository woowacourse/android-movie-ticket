package woowacourse.movie.utils

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun Activity.keyError(key: String) {
    Toast.makeText(this, "Key '$key' is not found in bundle", Toast.LENGTH_LONG).show()
    finish()
}

fun Activity.showAskDialog(
    title: String,
    message: String,
    positiveString: String,
    actionPositive: () -> Unit = {},
    negativeString: String,
    actionNegative: () -> Unit = {},
): AlertDialog = AlertDialog.Builder(this)
    .setCancelable(false)
    .setTitle(title)
    .setMessage(message)
    .setPositiveButton(positiveString) { _, _ -> actionPositive() }
    .setNegativeButton(negativeString) { _, _ -> actionNegative() }
    .show()
