package woowacourse.movie.util

import android.app.Activity
import androidx.appcompat.app.AlertDialog

object DialogUtil {
    private const val ERROR_TITLE = "오류"

    fun showError(
        activity: Activity,
        title: String = ERROR_TITLE,
        message: String,
    ) {
        AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("확인") { _, _ ->
                activity.finish()
            }
            .setCancelable(false)
            .show()
    }

    fun makeDialog(
        activity: Activity,
        title: String,
        message: String,
        positiveButtonName: String,
        negativeButtonName: String,
        moveTo: () -> Unit,
    ) {
        AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonName) { _, _ ->
                moveTo()
            }
            .setNegativeButton(negativeButtonName) { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false)
            .show()
    }
}
