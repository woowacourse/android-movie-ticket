package woowacourse.movie

import android.content.Intent
import android.os.Build
import woowacourse.movie.domain.Movie

class BuildVersion(
    private val buildVersion: Int = Build.VERSION.SDK_INT,
) {
    fun movie(intent: Intent): Movie {
        return if (buildVersion >= Build.VERSION_CODES.TIRAMISU) {
            @Suppress("NewApi")
            intent.getParcelableExtra(MovieTicketActivity.KEY_MOVIE, Movie::class.java)
                ?: throw IllegalStateException()
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(MovieTicketActivity.KEY_MOVIE) as? Movie
                ?: throw IllegalStateException()
        }
    }
}
