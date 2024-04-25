package woowacourse.movie.domain

interface MovieRepository {
    fun findAll(): List<Movie>

    fun findOneById(id: Long): Movie?
}
