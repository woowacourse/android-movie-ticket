package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import java.io.Serializable

inline fun <reified T : Serializable> Bundle.getSerializableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
}

fun View.setBackgroundColorId(color: Int) {
    setBackgroundColor(ContextCompat.getColor(context, color))
}
