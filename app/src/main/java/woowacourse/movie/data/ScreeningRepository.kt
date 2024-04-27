package woowacourse.movie.data

import woowacourse.movie.domain.screening.Screening

interface ScreeningRepository {
    fun findAll(): List<Screening>

    fun find(id: Long): Screening?
}
