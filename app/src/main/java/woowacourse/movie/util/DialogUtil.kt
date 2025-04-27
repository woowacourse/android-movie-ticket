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
}
