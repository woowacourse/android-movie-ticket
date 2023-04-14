package woowacourse.movie.utils

import android.os.Build
import androidx.fragment.app.Fragment
import java.io.Serializable

inline fun <reified T : Serializable> Fragment.getSerializableCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arguments?.getSerializable(key, T::class.java)
    } else {
        arguments?.getSerializable(key) as T?
    }

fun Fragment.showToast(message: String) {
    requireContext().showToast(message)
}
