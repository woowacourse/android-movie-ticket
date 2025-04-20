package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import kotlin.reflect.KClass

class BuildVersion {
    fun <T : Parcelable> getParcelableClass(intent: Intent, name: String, clazz: KClass<T>): T {
        return if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(name, clazz.java)
                ?: throw IllegalStateException()
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(name) as? T
                ?: throw IllegalStateException()
        }
    }
}
