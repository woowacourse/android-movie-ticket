package woowacourse.movie.ui.main.itemModel

import woowacourse.movie.R
import woowacourse.movie.model.AdvState

class AdvItemModel(
    val advState: AdvState,
    override val onClick: (position: Int) -> Unit
) : ItemModel {
    override val layoutId: Int = R.layout.adb_item_layout
}
