package woowacourse.movie.repository

import woowacourse.movie.domain.Movie

object MovieRepository {

    private val movies: MutableMap<Long, Movie> = mutableMapOf()
    private const val INIT_ID = 1L
    var nextId: Long = INIT_ID
        private set
    private const val NOT_IN_TURN_ID_ERROR = "순서에 맞는 아이디의 영화만 저장할 수 있습니다."

    fun save(movie: Movie) {
        require(movie.id in INIT_ID..nextId) { NOT_IN_TURN_ID_ERROR }
        if (movie.id !in movies.keys) nextId++
        movies[movie.id] = movie
    }

    fun findById(id: Long): Movie? {
        return movies[id]
    }

    fun findAll(): List<Movie> {
        return movies.values.toList()
    }
}
