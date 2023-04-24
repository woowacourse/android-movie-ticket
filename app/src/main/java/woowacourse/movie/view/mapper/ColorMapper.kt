package woowacourse.movie.view.mapper

interface ColorMapper<T> {
    fun T.matchColor(): Int
}
