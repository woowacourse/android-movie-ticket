package woowacourse.movie.ui.seatreservation

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.ui.completed.CompletedActivity

class SeatReservationDialog {

    fun init(context: Context, reservationInfo: ReservationInfo) {
        AlertDialog.Builder(context)
            .setCancelable(false)
            .setTitle(TITLE)
            .setMessage(CONTENT)
            .setPositiveButton(YES) { _, _ ->
                setEventOnPositiveButton(context, reservationInfo)
            }
            .setNegativeButton(NO) { dialog, _ ->
                setEventOnNegativeButton(dialog)
            }
            .show()
    }

    private fun setEventOnPositiveButton(context: Context, reservationInfo: ReservationInfo) {
        val intentToCompletedActivity = CompletedActivity.getIntent(context, reservationInfo)

        context.apply {
            startActivity(intentToCompletedActivity)
            (this as SeatReservationActivity).finish()
        }
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
