package woowacourse.movie.domain.model.movies

class Poster(
    val resource: String,
) {
    val posterId get() = resource.toIntOrNull()
}
