package woowacourse.movie.mapper

import android.content.res.Resources
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.ui.MovieUiModel
import woowacourse.movie.util.Formatter.simpleDateFormat

fun Movie.toUiModel(resources: Resources): MovieUiModel {
    val screeningStartDate = simpleDateFormat(screeningStartDate)
    val screeningEndDate = simpleDateFormat(screeningEndDate)
    return MovieUiModel(
        imageSource = getImageResIdFromUrl(imageSource),
        title = title,
        screeningPeriod =
            resources.getString(
                R.string.screening_date_period,
                screeningStartDate,
                screeningEndDate,
            ),
        runningTimeText = resources.getString(R.string.minute_text, runningTime),
    )
}

private fun getImageResIdFromUrl(imageUrl: String): Int {
    val resourceName = imageUrl.substringBeforeLast('.')
    return imageNameToResIdMap[resourceName] ?: 0
}

private val imageNameToResIdMap =
    mapOf(
        "harry_potter" to R.drawable.harry_potter,
    )
