package woowacourse.movie.ui.home.adapter

enum class HomeRecyclerViewType(val value: Int) {
    MOVIE(0), ADVERTISEMENT(1);

    companion object {
        fun from(viewType: Int): HomeRecyclerViewType {
            return values()[viewType]
        }
    }
}
