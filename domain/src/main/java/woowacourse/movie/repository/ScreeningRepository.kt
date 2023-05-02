package woowacourse.movie.repository

import woowacourse.movie.domain.Screening

object ScreeningRepository {

    private var next_id: Long = 1L
    private val screenings: MutableMap<Long, Screening> = mutableMapOf()

    fun save(screening: Screening) {
        if (screening.id == null) screening.id = next_id++
        screenings[screening.id!!] = screening
    }

    fun findById(id: Long): Screening? {
        return screenings[id]
    }

    fun findAll(): List<Screening> {
        return screenings.values.toList()
    }
}
