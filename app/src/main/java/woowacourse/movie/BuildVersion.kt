package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Parcelable
import kotlin.reflect.KClass

class BuildVersion {
    fun <T : Parcelable> getParcelableClass(intent: Intent, name: String, clazz: KClass<T>): T {
        return if (Build.VERSION.SDK_INT >= TIRAMISU) {
            intent.getParcelableExtra(name, clazz.java)
                ?: throw IllegalStateException("Intent에서 ${clazz}가 전달이 되지 않았습니다.")
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(name) as? T
                ?: throw IllegalStateException("Intent에서 ${clazz}가 전달이 되지 않았습니다.")
        }
    }
}
