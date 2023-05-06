package woowacourse.movie.repository

import woowacourse.movie.domain.theater.Theater

object TheaterRepository {

    private var next_id: Long = 1L
    private val theaters: MutableMap<Long, Theater> = mutableMapOf()

    init {
        initSampleData()
    }

    private fun initSampleData() {
        val theater = Theater(5, 4)
        save(theater)
    }

    fun save(theater: Theater) {
        if (theater.id == null) theater.id = next_id++
        theaters[theater.id!!] = theater
    }

    fun findAll(): List<Theater> {
        return theaters.values.toList()
    }
}
