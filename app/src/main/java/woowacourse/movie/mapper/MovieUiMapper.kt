package woowacourse.movie.mapper

import android.content.res.Resources
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieUiModel
import java.time.format.DateTimeFormatter

fun Movie.toUiModel(resources: Resources): MovieUiModel {
    val screeningStartDate = screeningStartDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
    val screeningEndDate = screeningEndDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
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
