package woowacourse.movie.ui.seatreservation.domain

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.ui.completed.CompletedActivity

class SeatReservationDialog {

    fun init(context: Context) {
        AlertDialog.Builder(context)
            .setCancelable(false)
            .setTitle(TITLE)
            .setMessage(CONTENT)
            .setPositiveButton(YES) { dialog, _ ->
                setEventOnPositiveButton(context)
            }
            .setNegativeButton(NO) { dialog, _ ->
                setEventOnNegativeButton(dialog)
            }
            .show()
    }

    private fun setEventOnPositiveButton(context: Context) {
        context.startActivity(
            Intent(
                context,
                CompletedActivity::class.java,
            ),
        )
    }

    private fun setEventOnNegativeButton(dialog: DialogInterface) {
        dialog.dismiss()
    }

    companion object {
        private const val TITLE = "예매 확인"
        private const val CONTENT = "정말 예매하시겠습니까?"
        private const val YES = "예매 완료"
        private const val NO = "취소"
    }
}
