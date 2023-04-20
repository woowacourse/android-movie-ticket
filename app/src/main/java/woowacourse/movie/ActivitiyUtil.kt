package woowacourse.movie

import android.app.Activity
import android.widget.Toast
import woowacourse.movie.activity.customGetSerializable

fun <T> Activity.getIntentData(key: String): T? {
    return intent.customGetSerializable(key) as? T ?: run {
        Toast.makeText(this, getString(R.string.get_intent_data_error_msg), Toast.LENGTH_SHORT).show()
        finish()
        null
    }
}
