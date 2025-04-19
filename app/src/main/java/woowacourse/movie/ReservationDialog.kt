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
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예매 완료") { _, _ ->
                val intent = Intent(context, CompleteActivity::class.java)
                intent.putExtra(Ticket.KEY_TICKET, ticket)
                context.startActivity(intent)
            }
            .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}
