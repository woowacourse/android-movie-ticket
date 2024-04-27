package woowacourse.movie.feature.home.ui

import androidx.annotation.DrawableRes
import woowacourse.movie.feature.home.list.MovieListViewType

class MovieAdvertisementUiModel(
    @DrawableRes
    val imageId: Int,
    override val viewType: MovieListViewType = MovieListViewType.ADVERTISEMENT,
) : MovieListUiModel
