package woowacourse.movie.util

import android.content.Intent
import android.os.Build
import woowacourse.movie.common_data.MovieDataSource
import woowacourse.movie.list.model.Movie
import woowacourse.movie.list.view.MovieListActivity.Companion.EXTRA_MOVIE_ID_KEY
import woowacourse.movie.reservation.model.Count
import woowacourse.movie.reservation.view.MovieReservationActivity.Companion.EXTRA_COUNT_KEY

object IntentUtil {
    fun getSerializableMovieData(intent: Intent): Movie {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_MOVIE_ID_KEY, Movie::class.java)
                ?: MovieDataSource.emptyMovie
        } else {
            intent.getSerializableExtra(EXTRA_MOVIE_ID_KEY) as Movie
        }
    }

    fun getSerializableCountData(intent: Intent): Count {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_COUNT_KEY, Count::class.java) ?: Count(1)
        } else {
            intent.getSerializableExtra(EXTRA_COUNT_KEY) as Count
        }
    }

//    fun getSerializableSeatsData(intent: Intent): List<Any?> {
//        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getSerializableExtra(SEATS_KEY, List::class.java) ?: listOf(Seat.of(-1, -1))
//        } else {
//            intent.getSerializableExtra(SEATS_KEY) as List<*>
//        }
//    }
}
