package woowacourse.movie.view.data

import java.io.Serializable

class AdvertisementViewData(
    val banner: ImageViewData,
    override val viewType: MovieListViewType = MovieListViewType.ADVERTISEMENT
) : Serializable, MovieListViewData
