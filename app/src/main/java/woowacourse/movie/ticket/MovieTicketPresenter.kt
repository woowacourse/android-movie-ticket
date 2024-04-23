package woowacourse.movie.ticket

class MovieTicketPresenter(
    val view: MovieTicketContract.View,
) : MovieTicketContract.Presenter {
    override val ticket = TicketDataResource.ticket

    override fun setTicketInfo() {
        view.showTicketInfo(ticket)
    }
}
