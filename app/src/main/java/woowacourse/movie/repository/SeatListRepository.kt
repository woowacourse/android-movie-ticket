package woowacourse.movie.repository

import woowacourse.movie.domain.SeatBoard

interface SeatListRepository {
    val seatBoards: List<SeatBoard>
}