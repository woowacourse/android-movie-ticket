package woowacourse.movie.ui.detail.view

import woowacourse.movie.ui.detail.ScreenDetailContract

interface TicketView {
    fun initClickListener(
        screenId: Int,
        presenter: ScreenDetailContract.Presenter,
    )

    fun updateTicketCount(count: Int)

    fun ticketCount(): Int

    fun restoreTicketCount(count: Int)
}
