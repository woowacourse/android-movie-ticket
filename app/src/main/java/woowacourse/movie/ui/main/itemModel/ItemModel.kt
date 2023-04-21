package woowacourse.movie.ui.main.itemModel

interface ItemModel {
    val layoutId: Int
    val onClick: (position: Int) -> Unit
}
