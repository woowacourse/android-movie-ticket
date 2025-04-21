package woowacourse.movie.view.reservation

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.R

class ReservationDialog {
    private var reservationDialog: AlertDialog? = null

    fun show(
        context: Context,
        title: String,
        message: String,
        negativeClick: ((DialogInterface) -> Unit)?,
        positiveClick: (DialogInterface) -> Unit,
    ) {
        if (reservationDialog == null) {
            reservationDialog = create(context, title, message, negativeClick, positiveClick)
        }

        reservationDialog?.show()
    }

    private fun create(
        context: Context,
        title: String,
        message: String,
        negativeClick: ((DialogInterface) -> Unit)?,
        positiveClick: (DialogInterface) -> Unit,
    ): AlertDialog =
        if (negativeClick == null) {
            AlertDialog
                .Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.reservation_error_dialog_confirm) { dialog, _ ->
                    positiveClick(dialog)
                }.create()
        } else {
            AlertDialog
                .Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton(R.string.reservation_dialog_cancel) { dialog, _ ->
                    negativeClick(dialog)
                }.setPositiveButton(R.string.reservation_dialog_complete) { dialog, _ ->
                    positiveClick(dialog)
                }.create()
        }
}
