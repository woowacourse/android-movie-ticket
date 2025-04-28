package woowacourse.movie.domain

import woowacourse.movie.uiModel.TicketUIModel

object TicketMaker {
    fun generator(
        title: String,
        date: String,
        time: String,
        count: Int,
    ): TicketUIModel {
        require(count > 0) { "발권 인원이 0명일 수 없습니다." }

        return TicketUIModel(
            title = title,
            date = date,
            time = time,
            count = count,
            money = 0,
        )
    }
}
