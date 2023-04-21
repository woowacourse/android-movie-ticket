package woowacourse.movie.presentation.extensions

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.R

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.createAlertDialog(
    isCancelable: Boolean = true,
    builder: AlertDialog.Builder.() -> Unit,
): AlertDialog =
    AlertDialog.Builder(this).apply {
        setCancelable(isCancelable)
        builder()
    }.create()

fun AlertDialog.Builder.title(text: String) {
    this.setTitle(text)
}

fun AlertDialog.Builder.message(text: String) {
    this.setMessage(text)
}

fun AlertDialog.Builder.positiveButton(
    text: String = context.getString(R.string.yes),
    onClick: (dialogInterface: DialogInterface) -> Unit = {},
) {
    this.setPositiveButton(text) { dialogInterface, _ -> onClick(dialogInterface) }
}

fun AlertDialog.Builder.negativeButton(
    text: String = context.getString(R.string.no),
    onClick: (dialogInterface: DialogInterface) -> Unit = {},
) {
    this.setNegativeButton(text) { dialogInterface, _ -> onClick(dialogInterface) }
}
