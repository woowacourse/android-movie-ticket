package woowacourse.movie.extensions

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showDialog(
    title: String,
    content: String,
    negativeButton: String,
    positiveButton: String,
    onNegativeButtonClick: () -> Unit,
    onPositiveButtonClick: () -> Unit
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(content)
        .setNegativeButton(negativeButton) { dialog, _ ->
            onNegativeButtonClick()
            dialog.dismiss()
        }
        .setPositiveButton(positiveButton) { dialog, _ ->
            onPositiveButtonClick()
            dialog.dismiss()
        }
        .setCancelable(false)
        .show()
}
