package woowacourse.movie.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun showAlertDialog(
    context: Context,
    title: String,
    message: String,
    positiveLabel: String,
    onPositiveButtonClicked: () -> Unit,
    negativeLabel: String,
) = AlertDialog.Builder(context)
    .setTitle(title)
    .setMessage(message)
    .setCancelable(false)
    .setPositiveButton(positiveLabel) { _, _ ->
        onPositiveButtonClicked()
    }
    .setNegativeButton(negativeLabel) { dialog, _ ->
        dialog.dismiss()
    }.show()
