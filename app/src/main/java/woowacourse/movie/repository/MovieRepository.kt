package woowacourse.movie.repository

import com.example.domain.Movie

interface MovieRepository {

    fun findAll(): List<Movie>
}
