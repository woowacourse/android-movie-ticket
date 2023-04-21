package woowacourse.movie.ui.main.itemModel

import woowacourse.movie.R
import woowacourse.movie.model.MovieState

class MovieItemModel(
    val movieState: MovieState,
    onClick: (position: Int) -> Unit
) : ItemModel {
    override val layoutId: Int = R.layout.movie_item_layout
    override fun onClick(position: Int) {
        onClick(position)
    }
}
