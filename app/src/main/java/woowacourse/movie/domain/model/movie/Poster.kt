package woowacourse.movie.domain.model.movie

sealed class Poster {
    data class Resource(
        val resId: Int,
    ) : Poster()

    data class Url(
        val url: String,
    ) : Poster()
}
