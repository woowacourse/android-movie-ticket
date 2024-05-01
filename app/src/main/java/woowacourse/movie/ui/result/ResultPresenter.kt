package woowacourse.movie.ui.result

import woowacourse.movie.domain.Ticket

class ResultPresenter(private val view: ResultContract.View) : ResultContract.Presenter {
    override fun loadResult(
        title: String,
        ticket: Ticket,
    ) {
        view.showResult(title, ticket)
    }
}
