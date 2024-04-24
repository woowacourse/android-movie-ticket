package woowacourse.movie.repository

import woowacourse.movie.model.screening.Screening

interface ScreeningRepository {
    fun getScreenings(): List<Screening>

    fun getScreening(screeningId: Int): Screening
}
