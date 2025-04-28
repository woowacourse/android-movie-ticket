package woowacourse.movie.domain

import woowacourse.movie.uiModel.Ticket

object TicketMaker {
    fun generator(
        title: String,
        date: String,
        time: String,
        count: Int,
    ): Ticket {
        require(count > 0) { "발권 인원이 0명일 수 없습니다." }

        return Ticket(
            title = title,
            date = date,
            time = time,
            count = count,
            money = 0,
        )
    }
}
