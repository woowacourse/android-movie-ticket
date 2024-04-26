package woowacourse.movie.model.data

import woowacourse.movie.model.data.dto.Movie

interface MovieRepository {
    fun save(movie: Movie): Long

    fun saveAll(movies: List<Movie>)

    fun find(id: Long): Movie

    fun findAll(): List<Movie>

    fun deleteAll()
}
