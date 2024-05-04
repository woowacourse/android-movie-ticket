package woowacourse.movie.presentation.adapter

enum class ListItemType(val code: Int) {
    MOVIE_TYPE(0),
    AD_TYPE(1), ;

    companion object {
        fun getTypeFromPosition(
            position: Int,
            adExposureCount: Int,
        ): Int {
            return if (position % adExposureCount == 0 && position > 0) {
                AD_TYPE.code
            } else {
                MOVIE_TYPE.code
            }
        }
    }
}
