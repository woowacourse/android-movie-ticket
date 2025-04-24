package woowacourse.movie.util

import android.app.Activity
import android.content.Intent
import android.os.Parcelable

object DataUtils {
    inline fun <reified T : Parcelable> getExtraOrFinish(
        intent: Intent,
        activity: Activity,
        extraDataKey: String,
    ): T? {
        return intent.getParcelableExtra(extraDataKey) ?: run {
            ErrorUtils.printError(activity)
            activity.finish()
            return null
        }
    }
}
