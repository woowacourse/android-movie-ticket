package woowacourse.movie.domain

interface MovieRepository {
    fun findAllMovies(): List<Movie>

    fun findMovieById(movieId: Long): Movie?

    fun findSeatsByMovieScreenDateTimeId(movieScreenDateTimeId: Long): List<MovieSeat>

    fun findSeatById(seatId: Long): MovieSeat?

    fun findScreenDateTimeByMovieScreenDateTimeId(movieScreenDateTimeId: Long): ScreenDateTime?
}
