package woowacourse.movie.view.model

enum class MovieAdapterViewType(val value: Int) {
    MOVIE(1),
    ADVERTISEMENT(2);

    companion object {
        fun find(number: Int): MovieAdapterViewType {
            return values().find { it.value == number } ?: throw IllegalArgumentException(
                VIEW_TYPE_ERROR
            )
        }

        private const val VIEW_TYPE_ERROR = "잘못된 viewtype이 들어왔어요"
    }
}
