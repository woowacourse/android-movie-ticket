package woowacourse.movie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.activity.CompleteActivity
import woowacourse.movie.domain.Ticket

class ReservationDialog(
    private val context: Context,
    private val ticket: Ticket,
) {
    fun popUp() {
        val title = context.getString(R.string.dialog_reservation_title)
        val message = context.getString(R.string.dialog_reservation_message)
        val positiveText = context.getString(R.string.dialog_reservation_positive_text)
        val negativeText = context.getString(R.string.dialog_reservation_negative_text)
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { _, _ ->
                val intent = Intent(context, CompleteActivity::class.java)
                intent.putExtra(Ticket.KEY_TICKET, ticket)
                context.startActivity(intent)
            }
            .setNegativeButton(negativeText) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}
