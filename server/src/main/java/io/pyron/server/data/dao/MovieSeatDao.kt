package io.pyron.server.data.dao

import io.pyron.server.data.db.dbMovieSeatBoards
import io.pyron.server.data.db.dbSeats
import io.pyron.server.data.entity.MovieSeat

class MovieSeatDao {
    fun findAllByMovieScreenDateTimeId(movieScreenDateTimeId: Long): List<MovieSeat> {
        val movieSeatBoard =
            dbMovieSeatBoards.first {
                it.movieScreenDateTimeId == movieScreenDateTimeId
            }
        return dbSeats.filter {
            it.movieSeatBoardId == movieSeatBoard.id
        }
    }

    fun findOneById(id: Long): MovieSeat? {
        return dbSeats.firstOrNull { it.id == id }
    }
}
