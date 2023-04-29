package woowacourse.movie.ui.main.itemModel

import woowacourse.movie.model.AdvState
import woowacourse.movie.ui.main.ViewType

class AdvItemModel(
    val advState: AdvState,
    override val onClick: (position: Int) -> Unit
) : ItemModel {
    override val viewType = ViewType.ADV
}
