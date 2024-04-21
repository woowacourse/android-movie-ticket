package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.Image
import woowacourse.movie.domain.model.Movie2

interface MovieRepository {
    fun findById(id: Int): Movie2

    fun imageSrc(id: Int): Image<Any>
}
