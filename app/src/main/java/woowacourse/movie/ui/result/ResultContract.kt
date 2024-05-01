package woowacourse.movie.ui.result

import woowacourse.movie.domain.Ticket

interface ResultContract {
    interface View {
        fun showResult(
            title: String,
            ticket: Ticket,
        )
    }

    interface Presenter {
        fun loadResult(
            title: String,
            ticket: Ticket,
        )
    }
}
