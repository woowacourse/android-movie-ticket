package woowacourse.movie.feature.home.ui

import android.graphics.drawable.Drawable
import woowacourse.movie.feature.home.list.MovieListViewType

class MovieUiModel(
    val posterImageDrawable: Drawable?,
    val titleMessage: String,
    val screeningDateMessage: String,
    val runningTimeMessage: String,
    val id: Long,
    override val viewType: MovieListViewType = MovieListViewType.MOVIE,
) : MovieListUiModel
