package woowacourse.movie.feature.home.ui

import android.graphics.drawable.Drawable

class MovieListUiModel(
    val posterImageDrawable: Drawable?,
    val titleMessage: String,
    val screeningDateMessage: String,
    val runningTimeMessage: String,
    val id: Long,
)
