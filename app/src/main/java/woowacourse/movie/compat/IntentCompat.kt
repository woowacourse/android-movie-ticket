package woowacourse.movie.compat

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import java.util.ArrayList

object IntentCompat {
    fun <T> getParcelableExtra(
        intent: Intent,
        name: String,
        clazz: Class<T>,
    ): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(name, clazz)
        } else {
            intent.getParcelableExtra(name)
        }
    }

    // 제네릭 타입은 Parcelable 하위 타입이여야만 한다.
    fun <T : Parcelable> getParcelableArrayListExtra(
        intent: Intent,
        name: String,
        clazz: Class<T>,
    ): ArrayList<T>? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(name, clazz)
        } else {
            intent.getParcelableArrayListExtra(name)
        }
    }
}
