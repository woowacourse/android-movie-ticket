package woowacourse.movie.feature.home.ui

import woowacourse.movie.feature.home.list.MovieListViewType

class MovieAdvertisementUiModel(
    val imageId: Int,
    override val viewType: MovieListViewType = MovieListViewType.ADVERTISEMENT,
) : MovieListUiModel
