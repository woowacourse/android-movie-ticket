package woowacourse.movie.view.reservation

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class ShowReservationDialog(
    context: Context,
) {
    private val builder = AlertDialog.Builder(context)

    operator fun invoke(
        title: String,
        message: String,
        positiveButtonText: String,
        positiveButtonAction: (dialog: DialogInterface, which: Int) -> Unit,
        negativeButtonText: String,
        negativeButtonAction: (dialog: DialogInterface, which: Int) -> Unit,
        cancelable: Boolean = false,
    ) {
        builder
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                positiveButtonText,
                positiveButtonAction,
            ).setNegativeButton(
                negativeButtonText,
                negativeButtonAction,
            ).setCancelable(cancelable)
            .show()
    }
}
