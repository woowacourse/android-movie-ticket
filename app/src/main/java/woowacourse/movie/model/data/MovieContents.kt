package woowacourse.movie.model.data

import woowacourse.movie.model.data.dto.MovieContent

interface MovieContents {
    fun save(movieContent: MovieContent): Long

    fun find(id: Long): MovieContent

    fun findAll(): List<MovieContent>

    fun deleteAll()
}
