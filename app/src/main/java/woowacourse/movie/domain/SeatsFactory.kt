package woowacourse.movie.domain

interface SeatsFactory {
    fun get(): List<Seat>
}
