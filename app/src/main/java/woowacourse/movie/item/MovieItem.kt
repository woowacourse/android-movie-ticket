package woowacourse.movie.item

import woowacourse.movie.uimodel.MovieModel

class MovieItem(
    val movieModel: MovieModel,
    override val itemType: ItemType = ItemType.MOVIE
) : ModelItem
