package woowacourse.movie.ui.main.itemModel

import woowacourse.movie.ui.main.ViewType

interface ItemModel {
    val viewType: ViewType
    val onClick: (position: Int) -> Unit
}
