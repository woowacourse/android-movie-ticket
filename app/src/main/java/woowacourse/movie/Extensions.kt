package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import java.io.Serializable

inline fun <reified T : Serializable> Intent.getSerializable(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializableExtra(key, T::class.java)
    } else {
        getSerializableExtra(key) as? T
    }

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
