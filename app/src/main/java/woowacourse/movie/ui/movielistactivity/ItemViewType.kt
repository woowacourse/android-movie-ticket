package woowacourse.movie.ui.movielistactivity

enum class ItemViewType(val position: Int) {
    MOVIE(0), ADVERTISEMENT(1);

    companion object {
        private const val ERROR_INPUT_VIE_TYPE = "해당 view type 을 찾지 못했습니다."

        fun of(value: Int) = ItemViewType.values()
            .find { it.position == value }
            ?: throw IllegalArgumentException(ERROR_INPUT_VIE_TYPE)
    }
}
