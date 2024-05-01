package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import woowacourse.movie.reservation.model.Count
import woowacourse.movie.reservation.view.MovieReservationActivity.Companion.EXTRA_COUNT_KEY
import java.io.Serializable

object IntentUtil {
    inline fun <reified T : Serializable> Intent.getSerializableCountData(): T {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(EXTRA_COUNT_KEY) as? T ?: Count(1) as T
        } else {
            this.getSerializableExtra(EXTRA_COUNT_KEY) as T
        }
    }
}
