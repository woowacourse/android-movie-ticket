package woowacourse.movie.data

import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.feature.main.ui.ScreeningItem

interface ScreeningRepository {
    fun findAll(): List<ScreeningItem>

    fun find(id: Long): Screening?
}
