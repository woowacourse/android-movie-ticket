package woowacourse.movie.model

enum class MovieRecyclerItemViewType {
    MOVIE, ADVERTISEMENT;

    companion object {
        private const val ADVERTISEMENT_TURN = 3
        private const val CYCLE = 4

        fun valueOf(position: Int): MovieRecyclerItemViewType {
            return when (position % CYCLE) {
                ADVERTISEMENT_TURN -> ADVERTISEMENT
                else -> MOVIE
            }
        }
    }
}
