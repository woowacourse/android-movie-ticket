package woowacourse.movie

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogFactory {
    fun create(
        dialogInfo: DialogInfo,
        onConfirmed: () -> Unit,
    ): AlertDialog {
        val builder =
            AlertDialog.Builder(dialogInfo.context)
                .setTitle(dialogInfo.title)
                .setMessage(dialogInfo.message)
                .setPositiveButton(dialogInfo.positiveMessage) { _, _ ->
                    onConfirmed()
                }
        if (dialogInfo.negativeMessage != null) {
            builder.setNegativeButton(dialogInfo.negativeMessage) { dialog, _ -> dialog.dismiss() }
        }
        builder.setCancelable(false)
        return builder.create()
    }

    fun createErrorDialog(
        context: Context,
        onConfirmed: () -> Unit,
    ): AlertDialog {
        val dialogInfo =
            DialogInfo(
                context,
                R.string.error,
                R.string.wrong_approach,
                R.string.confirm,
                null,
            )
        return create(dialogInfo, onConfirmed)
    }
}
