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
        AlertDialog.Builder(context)
            .setTitle(context.getText(R.string.reserve_confirm))
            .setMessage(context.getText(R.string.askFor_reserve))
            .setPositiveButton(context.getText(R.string.complete)) { _, _ ->
                val intent = Intent(context, CompleteActivity::class.java)
                intent.putExtra("ticket", ticket)
                context.startActivity(intent)
            }
            .setNegativeButton(context.getText(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}
