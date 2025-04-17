package woowacourse.movie

import android.app.AlertDialog
import android.content.Context
import woowacourse.movie.data.Ticket

object ConfirmDialog {
    fun show(
        context: Context,
        ticket: Ticket,
        onConfirmed: () -> Unit,
    ) {
        AlertDialog
            .Builder(context)
            .setTitle(R.string.confirm_booking_title)
            .setMessage(R.string.confirm_booking_message)
            .setPositiveButton(R.string.yes) { _, _ -> onConfirmed() }
            .setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}
