package woowacourse.movie

import android.app.AlertDialog
import android.content.Context
import woowacourse.movie.data.Ticket

object ConfirmDialog {
    fun show(context: Context, ticket: Ticket, onConfirmed: () -> Unit) {
        AlertDialog.Builder(context)
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예") { _, _ -> onConfirmed() }
            .setNegativeButton("아니요") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}
