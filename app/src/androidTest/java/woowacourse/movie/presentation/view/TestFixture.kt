package woowacourse.movie.presentation.view

import android.content.Context
import android.content.Intent
import woowacourse.movie.R
import woowacourse.movie.presentation.ui.detail.MovieDetailActivity
import woowacourse.movie.presentation.ui.reservation.ReservationResultActivity

val posterImageSrc = R.drawable.harrypotter_poster
const val TITLE = "title"
const val SCREENING_DATE = "2024.04.18"
const val RUNNING_TIME = 100
const val SUMMARY = "summary"
const val RESERVATION_COUNT = 2
const val TOTAL_PRICE = 26000
const val EXTRA_MOVIE_ID = "movieId"

fun detailActivityIntent(context: Context): Intent =
    Intent(context, MovieDetailActivity::class.java).apply {
        putExtra(EXTRA_MOVIE_ID, 1)
        putExtra("posterSrc", posterImageSrc)
        putExtra("title", TITLE)
        putExtra("screeningDate", SCREENING_DATE)
        putExtra("runningTime", RUNNING_TIME)
        putExtra("summary", SUMMARY)
    }

fun reservationResultActivityIntent(context: Context): Intent =
    Intent(context, ReservationResultActivity::class.java).apply {
        putExtra("title", TITLE)
        putExtra("screeningDate", SCREENING_DATE)
        putExtra("reservationCount", RESERVATION_COUNT)
        putExtra("totalPrice", TOTAL_PRICE)
    }
