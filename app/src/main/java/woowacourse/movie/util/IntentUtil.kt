package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import woowacourse.movie.reservation.model.Count
import woowacourse.movie.reservation.view.MovieReservationActivity.Companion.EXTRA_COUNT_KEY

object IntentUtil {
    fun getSerializableCountData(intent: Intent): Count {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_COUNT_KEY, Count::class.java) ?: Count(1)
        } else {
            intent.getSerializableExtra(EXTRA_COUNT_KEY) as Count
        }
    }
}
