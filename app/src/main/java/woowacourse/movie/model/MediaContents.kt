package woowacourse.movie.model

class MediaContents(
    val value: List<MediaContent>,
) {
    fun mediaContentsType(position: Int): Int {
        return when (value[position]) {
            is Movie -> TYPE_MOVIE
            is Advertisement -> TYPE_ADVERTISEMENT
        }
    }

    companion object {
        const val TYPE_MOVIE = 1
        const val TYPE_ADVERTISEMENT = 2
    }
}
