package woowacourse.movie.presentation.model.movieitem

sealed interface ListItem {
    fun isAd(): Boolean
}
