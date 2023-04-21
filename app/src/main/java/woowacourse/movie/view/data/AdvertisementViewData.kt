package woowacourse.movie.view.data

import java.io.Serializable

class AdvertisementViewData(override val viewType: MovieListViewType = MovieListViewType.ADVERTISEMENT) :
    Serializable,
    MovieListViewData
