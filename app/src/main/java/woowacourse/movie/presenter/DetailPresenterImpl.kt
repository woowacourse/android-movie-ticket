package woowacourse.movie.presenter

import woowacourse.movie.model.Ticket

class DetailPresenterImpl(private val view: DetailContract.View) : DetailContract.Presenter {
    override fun onMinusButtonClicked(count: Int) {
        if (count > 1) {
            view.updateCounter(count - 1)
        }
    }

    override fun onPlusButtonClicked(count: Int) {
        view.updateCounter(count + 1)
    }

    override fun onReserveButtonClicked(ticket: Ticket) {
        view.startTicketActivity(ticket)
    }
}
