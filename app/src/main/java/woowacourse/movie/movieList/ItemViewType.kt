package woowacourse.movie.movieList

enum class ItemViewType(val value: Int) {
    ITEM_VIEW_TYPE_MOVIE(0),
    ITEM_VIEW_TYPE_AD(1),
    ;

    companion object {
        fun of(value: Int): ItemViewType {
            return when (value) {
                ITEM_VIEW_TYPE_MOVIE.value -> ITEM_VIEW_TYPE_MOVIE
                ITEM_VIEW_TYPE_AD.value -> ITEM_VIEW_TYPE_AD
                else -> throw IllegalArgumentException()
            }
        }

        const val ITEM_VIEW_TYPE_MAX = 2
    }
}
