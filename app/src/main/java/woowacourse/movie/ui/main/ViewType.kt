package woowacourse.movie.ui.main

enum class ViewType(val id: Int) {
    MOVIE(0),
    ADV(1);

    companion object {
        fun of(id: Int): ViewType = values()[id]
    }
}
