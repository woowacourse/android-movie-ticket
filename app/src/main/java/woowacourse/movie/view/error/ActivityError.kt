package woowacourse.movie.view.error

import android.app.Activity
import android.util.Log
import android.widget.Toast

object ActivityError {
    private const val LOG_TAG = ""
    private const val ERROR_LOG_MESSAGE = "%s 액티비티에서 %s Extra가 필요합니다."
    private const val ERROR_TOAST_MESSAGE = "액티비티 실행에 오류가 발생했습니다."

    fun Activity.finishWithError(activityMissingExtras: ViewError.ActivityMissingExtras) {
        Log.d(LOG_TAG, ERROR_LOG_MESSAGE.format(localClassName, activityMissingExtras.message))
        Toast.makeText(this, ERROR_TOAST_MESSAGE, Toast.LENGTH_SHORT).show()
        finish()
    }
}
