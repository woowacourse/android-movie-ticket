package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import woowacourse.movie.model.Movie

object IntentUtil {
    fun getSerializableMovieData(intent: Intent): Movie? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("movie", Movie::class.java)
        } else {
            intent.getSerializableExtra("movie") as? Movie
        }
    }
}
