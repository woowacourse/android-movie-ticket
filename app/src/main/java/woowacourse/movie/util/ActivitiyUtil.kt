package woowacourse.movie

import android.app.Activity
import android.widget.Toast
import woowacourse.movie.activity.getSerializableExtraByKey

fun <T> Activity.getIntentData(key: String): T? {
    return intent.getSerializableExtraByKey(key) as? T ?: run {
        Toast.makeText(this, getString(R.string.get_intent_data_error_msg), Toast.LENGTH_SHORT).show()
        finish()
        null
    }
}
