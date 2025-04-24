package woowacourse.movie.domain.model

sealed class Poster {
    data class Resource(
        val resId: Int,
    ) : Poster()

    data class Url(
        val url: String,
    ) : Poster()
}
