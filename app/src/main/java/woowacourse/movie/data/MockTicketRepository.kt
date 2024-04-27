package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.screening.Movie
import java.time.LocalDateTime

object MockTicketRepository : TicketRepository {
    private val MOCK_MOVIE =
        Movie(
            R.drawable.sorcerer_ston_poster,
            "제목",
            "설명",
            120,
        )
    private var id: Long = 1L
    private val tickets: MutableList<Ticket> =
        mutableListOf(
            Ticket(
                0,
                MOCK_MOVIE,
                LocalDateTime.now(),
                listOf("A1"),
                10000,
            ),
        )

    override fun findAll(): List<Ticket> = tickets.toList()

    override fun find(id: Long): Ticket? = tickets.find { it.id == id }

    override fun save(
        movie: Movie,
        schedule: LocalDateTime,
        seats: List<String>,
        price: Long,
    ): Long {
        val item =
            Ticket(
                id,
                movie.copy(),
                schedule,
                seats,
                price,
            ).copy()
        tickets.add(item)
        return id++
    }
}
