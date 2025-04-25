package woowacourse.movie.domain

sealed class ListItemType {
    data class MovieItem(
        val movie: Movie,
    ) : ListItemType()

    object AdItem : ListItemType() // 광고 아이템
}
