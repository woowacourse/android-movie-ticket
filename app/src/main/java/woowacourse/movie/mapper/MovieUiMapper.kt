package woowacourse.movie.mapper

import android.content.res.Resources
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.ui.MovieUiModel
import woowacourse.movie.util.Formatter.formatDateDotSeparated

fun Movie.toUiModel(resources: Resources): MovieUiModel {
    val screeningStartDate = formatDateDotSeparated(screeningStartDate)
    val screeningEndDate = formatDateDotSeparated(screeningEndDate)
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
    return imageNameToResIdMap[resourceName] ?: R.drawable.harry_potter
}

private val imageNameToResIdMap =
    mapOf(
        "harry_potter" to R.drawable.harry_potter,
        "star_is_born" to R.drawable.star_is_born,
    )
