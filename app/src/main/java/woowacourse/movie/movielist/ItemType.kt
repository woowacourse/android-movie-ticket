package woowacourse.movie.movielist

import woowacourse.movie.domain.Movie

sealed class ItemType {
    data class MovieType(val movie: Movie) : ItemType()

    data object ADType : ItemType()
}
