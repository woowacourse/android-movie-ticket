package woowacourse.movie.ui.main.itemModel

interface ItemModel {
    val layoutId: Int
    fun onClick(position: Int)
}
