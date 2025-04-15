package woowacourse.movie.domain

data class Movies(
    val movies: Map<String, Movie>,
) {
    fun find(title: String): Movie {
        return movies[title] ?: throw IllegalStateException(ERR_MOVIE_NOT_FOUND)
    }

    companion object {
        private const val ERR_MOVIE_NOT_FOUND = "영화가 존재하지 않습니다."
    }
}