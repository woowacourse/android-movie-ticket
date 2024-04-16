package woowacourse.movie.dao

import woowacourse.movie.model.MovieContent

interface MovieContents {
    fun save(movieContent: MovieContent): Long

    fun find(id: Long): MovieContent

    fun findAll(): List<MovieContent>
}
