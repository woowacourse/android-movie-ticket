package woowacourse.movie

import android.content.Context
import androidx.appcompat.app.AlertDialog

class ReservationDialog(
    private val context: Context,
) {
    fun popUp(onPositiveClick: () -> Unit) {
        val title = context.getString(R.string.dialog_reservation_title)
        val message = context.getString(R.string.dialog_reservation_message)
        val positiveText = context.getString(R.string.dialog_reservation_positive_text)
        val negativeText = context.getString(R.string.dialog_reservation_negative_text)
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { _, _ ->
                onPositiveClick()
            }
            .setNegativeButton(negativeText) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }
}
