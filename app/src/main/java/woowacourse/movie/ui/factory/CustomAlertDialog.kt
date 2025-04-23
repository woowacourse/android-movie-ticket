package woowacourse.movie.ui.factory

import android.content.Context
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.R

class CustomAlertDialog(private val context: Context) {
    private var alertDialog: AlertDialog? = null
    private var currentDialogInfo: DialogInfo? = null

    fun show(dialogInfo: DialogInfo) {
        initAlertDialog(dialogInfo)
        alertDialog?.show()
    }

    private fun initAlertDialog(dialogInfo: DialogInfo) {
        if (alertDialog == null) create(dialogInfo)
        if (currentDialogInfo == dialogInfo) update(dialogInfo, alertDialog ?: defaultDialog())
        currentDialogInfo = dialogInfo
    }

    private fun create(dialogInfo: DialogInfo) {
        alertDialog =
            AlertDialog.Builder(context).create().apply {
                update(dialogInfo, this)
            }
    }

    private fun update(
        dialogInfo: DialogInfo,
        alertDialog: AlertDialog,
    ) {
        alertDialog.apply {
            setTitle(dialogInfo.title)
            setMessage(dialogInfo.message)
            setButton(AlertDialog.BUTTON_POSITIVE, dialogInfo.positiveMessage) { _, _ ->
                dialogInfo.onConfirm()
            }
            dialogInfo.negativeMessage?.let { negativeMessage ->
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, negativeMessage) { dialog, _ ->
                    dialogInfo.onDismiss()
                    dialog.dismiss()
                }
            }
        }
    }

    private fun defaultDialog(): AlertDialog =
        AlertDialog.Builder(context).apply {
            setTitle("다이얼로그 미초기화")
            setMessage("다이얼로그가 초기화 되지 않았습니다")
            setPositiveButton(context.getString(R.string.confirm)) { dailog, _ ->
                dailog.dismiss()
            }
        }.create()
}
