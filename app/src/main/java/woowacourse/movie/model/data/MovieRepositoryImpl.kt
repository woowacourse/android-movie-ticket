package woowacourse.movie.model.data

import woowacourse.movie.model.data.dto.Movie

object MovieRepositoryImpl : MovieRepository {
    private val movies = mutableMapOf<Long, Movie>()
    private const val NOT_EXIST_ID_MESSAGE = "해당하는 아이디의 영화를 찾을 수 없습니다."
    private const val EXIST_ID_MESSAGE = "해당하는 아이디의 영화가 이미 존재합니다."

    init {
        saveAll(movieDummy)
    }

    override fun save(movie: Movie) {
        check(!movies.contains(movie.id)) { EXIST_ID_MESSAGE }
        movies[movie.id] = movie
    }

    override fun saveAll(movies: List<Movie>) {
        movies.forEach { save(it) }
    }

    override fun find(id: Long): Movie {
        return movies[id] ?: throw IllegalArgumentException(NOT_EXIST_ID_MESSAGE)
    }

    override fun findAll(): List<Movie> {
        return movies.map { it.value }
    }

    override fun deleteAll() {
        movies.clear()
    }
}
