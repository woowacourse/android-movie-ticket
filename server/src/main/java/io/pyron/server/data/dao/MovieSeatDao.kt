package io.pyron.server.data.dao

import io.pyron.server.data.db.DB_MOVIESEATBOARDS
import io.pyron.server.data.db.DB_SEATS
import io.pyron.server.data.entity.MovieSeat

class MovieSeatDao {
    fun findAllByMovieScreenDateTime(movieScreenDateTimeId: Long): List<MovieSeat> {
        val movieSeatBoard =
            DB_MOVIESEATBOARDS.first {
                it.movieScreenDateTimeId == movieScreenDateTimeId
            }
        return DB_SEATS.filter {
            it.movieSeatBoardId == movieSeatBoard.id
        }
    }

    fun findOneById(seatId: Long): MovieSeat? {
        return DB_SEATS.firstOrNull { it.id == seatId }
    }
}
