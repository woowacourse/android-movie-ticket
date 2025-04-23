package woowacourse.movie.view.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.R

class DialogFactory {
    private var dialog: AlertDialog? = null
    private var errorDialog: AlertDialog? = null

    fun show(
        dialogInfo: DialogInfo,
        onConfirmed: () -> Unit,
    ) {
        if (dialog == null) {
            dialog = create(dialogInfo, onConfirmed)
        }

        dialog?.show()
    }

    fun showError(
        context: Context,
        onConfirmed: () -> Unit,
    ) {
        if (errorDialog == null) {
            errorDialog = createErrorDialog(context, onConfirmed)
        }
        errorDialog?.show()
    }

    private fun create(
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

    private fun createErrorDialog(
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
