package woowacourse.movie.data

import woowacourse.movie.model.MovieContent

object MovieContentsImpl : MovieContents {
    private var id: Long = 0
    private val movieContents = mutableMapOf<Long, MovieContent>()

    override fun save(movieContent: MovieContent): Long {
        movieContents[id] = movieContent.copy(id = id)
        return id++
    }

    override fun find(id: Long): MovieContent {
        return movieContents[id]!!
    }

    override fun findAll(): List<MovieContent> {
        return movieContents.map { it.value }
    }
}
