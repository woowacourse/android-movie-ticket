package woowacourse.movie.feature.home.ui

import androidx.annotation.DrawableRes
import woowacourse.movie.feature.home.list.MovieListViewType

class MovieUiModel(
    @DrawableRes
    val posterImageId: Int,
    val titleMessage: String,
    val screeningDateMessage: String,
    val runningTimeMessage: String,
    val id: Long,
    override val viewType: MovieListViewType = MovieListViewType.MOVIE,
) : MovieListUiModel
