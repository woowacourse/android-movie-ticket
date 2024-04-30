package woowacourse.movie.model.data

import woowacourse.movie.model.movie.UserTicket

interface UserTickets {
    fun save(userTicket: UserTicket): Long

    fun find(id: Long): UserTicket

    fun deleteAll()
}
