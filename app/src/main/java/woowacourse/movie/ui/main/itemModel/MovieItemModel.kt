package woowacourse.movie.ui.main.itemModel

import woowacourse.movie.model.MovieState
import woowacourse.movie.ui.main.ViewType

class MovieItemModel(
    val movieState: MovieState,
    override val onClick: (position: Int) -> Unit
) : ItemModel {
    override val viewType: ViewType = ViewType.MOVIE
}
