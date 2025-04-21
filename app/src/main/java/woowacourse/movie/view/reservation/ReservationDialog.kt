package woowacourse.movie.view.reservation

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class ReservationDialog {
    private var reservationDialog: AlertDialog? = null

    fun show(
        context: Context,
        title: String,
        message: String,
        negativeClick: (DialogInterface) -> Unit,
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
        negativeClick: (DialogInterface) -> Unit,
        positiveClick: (DialogInterface) -> Unit,
    ): AlertDialog =
        AlertDialog
            .Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setNegativeButton("adsasd") { dialog, _ ->
                negativeClick(dialog)
            }.setPositiveButton("adsasd") { dialog, _ ->
                positiveClick(dialog)
            }.create()
}
