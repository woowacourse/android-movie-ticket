package woowacourse.movie.presentation.extensions

import android.os.Build
import android.os.Parcelable
import androidx.fragment.app.Fragment
import woowacourse.movie.utils.showToast

inline fun <reified T : Parcelable> Fragment.getParcelableCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments?.getParcelable(key, T::class.java)
    } else {
        arguments?.getParcelable(key) as T?
    }

fun Fragment.showToast(message: String) {
    requireContext().showToast(message)
}
