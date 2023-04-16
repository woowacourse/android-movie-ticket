package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast

inline fun <reified T : Parcelable> Intent.getParcelable(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        getParcelableExtra(key) as? T
    }

inline fun <reified T : Parcelable> Bundle.customGetParcelable(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.get(key)
        getParcelable(key, T::class.java)
    } else {
        getParcelable(key) as? T
    }

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
