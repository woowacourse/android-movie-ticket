package woowacourse.movie.movielist

import woowacourse.movie.domain.Movie

sealed class FeedItem {
    data class MovieFeed(val movie: Movie) : FeedItem()

    data object ADFeed : FeedItem()
}
