package woowacourse.movie.view.extension

import android.content.Intent
import android.os.Build
import java.io.Serializable

const val SERIALIZABLE_EXTRA_ERROR_MESSAGE = "Serializable extra '%s'를 찾을 수 없습니다."

inline fun <reified T : Serializable> Intent.getSerializableExtraData(key: String): T =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requireNotNull(
            getSerializableExtra(
                key,
                T::class.java,
            ),
        ) { SERIALIZABLE_EXTRA_ERROR_MESSAGE.format(key) }
    } else {
        requireNotNull(getSerializableExtra(key) as? T) {
            SERIALIZABLE_EXTRA_ERROR_MESSAGE.format(
                key,
            )
        }
    }
