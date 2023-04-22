package woowacourse.movie.ui.main.itemModel

import androidx.annotation.LayoutRes

interface ItemModel {
    @get:LayoutRes
    val layoutId: Int
    val onClick: (position: Int) -> Unit
}
