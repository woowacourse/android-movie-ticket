package woowacourse.movie.view.util

import android.app.AlertDialog
import android.content.Context

class CustomAlertDialog(
    private val context: Context,
) {
    private var dialog: AlertDialog? = null

    fun show(dialogInfo: DialogInfo) {
        if (dialog == null) {
            dialog = create(dialogInfo)
        } else {
            update(dialog!!, dialogInfo)
        }
        dialog?.show()
    }

    private fun create(dialogInfo: DialogInfo): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(dialogInfo.isCancelable)
        return builder.create().apply {
            update(this, dialogInfo)
        }
    }

    private fun update(
        dialog: AlertDialog,
        dialogInfo: DialogInfo,
    ) {
        dialog.setTitle(dialogInfo.title)
        dialog.setMessage(dialogInfo.message)

        dialog.setButton(AlertDialog.BUTTON_POSITIVE, dialogInfo.positiveButtonText ?: "") { _, _ ->
            dialogInfo.onClickPositiveButton(dialog)
        }

        dialogInfo.negativeButtonText?.let {
            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, it) { _, _ ->
                dialogInfo.onClickNegativeButton(dialog)
            }
        }
    }
}
