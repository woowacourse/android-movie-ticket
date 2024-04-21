package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Image
import woowacourse.movie.domain.model.Movie

interface MovieRepository {
    fun findById(id: Int): Movie

    fun imageSrc(id: Int): Image<Any>
}
