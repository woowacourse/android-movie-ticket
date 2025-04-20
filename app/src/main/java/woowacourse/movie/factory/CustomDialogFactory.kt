package woowacourse.movie.factory

import android.content.Context
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.R

class CustomDialogFactory {
    fun emptyValueDialog(
        context: Context,
        title: String,
        message: String,
        onConfirm: () -> Unit,
    ): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.confirm)) { _, _ ->
                onConfirm()
            }
            .setCancelable(false)
            .create()
    }

    fun selectDialog(
        context: Context,
        title: String,
        message: String,
        positiveMessage: String = context.getString(R.string.confirm),
        negativeMessage: String = context.getString(R.string.cancel),
        onConfirm: () -> Unit,
    ): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveMessage) { _, _ -> onConfirm() }
            .setNegativeButton(negativeMessage) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .create()
    }
}
