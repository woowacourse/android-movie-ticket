package woowacourse.movie.presentation.extension

import android.content.Intent
import android.os.Build
import android.os.Parcelable

@Suppress("DEPRECATION")
internal inline fun <reified T : Parcelable> Intent.getParcelableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        getParcelableExtra(key) as? T
    }
}
