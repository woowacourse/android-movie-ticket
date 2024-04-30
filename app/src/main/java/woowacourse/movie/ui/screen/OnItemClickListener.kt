package woowacourse.movie.ui.screen

fun interface OnItemClickListener<T> {
    fun onClick(itemId: T)
}
