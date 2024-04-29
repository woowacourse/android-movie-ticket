package woowacourse.movie.view.home.adapter

enum class CatalogViewType(val viewType: Int, val interval: Int = 0, val position: Int = 0) {
    MOVIE(0),
    ADVERTISEMENT(
        viewType = 1,
        interval = 4,
        position = 3,
    ), ;

    companion object {
        fun from(viewType: Int): CatalogViewType {
            return entries.first { it.viewType == viewType }
        }
    }
}
