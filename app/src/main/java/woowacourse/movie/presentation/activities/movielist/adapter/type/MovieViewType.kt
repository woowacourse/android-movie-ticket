package woowacourse.movie.presentation.activities.movielist.adapter.type

enum class MovieViewType(val type: Int) {
    AD(0),
    MOVIE(1);

    companion object {
        fun get(type: Int): MovieViewType =
            values().first { it.type == type }
    }
}
