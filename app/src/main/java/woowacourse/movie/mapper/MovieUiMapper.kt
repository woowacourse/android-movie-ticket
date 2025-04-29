package woowacourse.movie.mapper

import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.movie.MovieUiModel

fun Movie.toUiModel(): MovieUiModel {
    return MovieUiModel(
        imageSource = getImageResIdFromUrl(imageSource),
        imageUrl = imageSource,
        title = title,
        screeningStartDate = screeningStartDate,
        screeningEndDate = screeningEndDate,
        runningTime = runningTime,
    )
}

fun MovieUiModel.toDomain(): Movie {
    return Movie(
        imageSource = imageUrl,
        title = title,
        screeningStartDate = screeningStartDate,
        screeningEndDate = screeningEndDate,
        runningTime = runningTime,
    )
}

private fun getImageResIdFromUrl(imageUrl: String): Int {
    val resourceName = imageUrl.substringBeforeLast('.')
    return imageNameToResIdMap[resourceName] ?: R.drawable.harry_potter
}

private val imageNameToResIdMap =
    mapOf(
        "harry_potter" to R.drawable.harry_potter,
        "harry_potter2" to R.drawable.harry_potter2,
        "harry_potter3" to R.drawable.harry_potter3,
        "harry_potter4" to R.drawable.harry_potter4,
        "star_is_born" to R.drawable.star_is_born,
    )
