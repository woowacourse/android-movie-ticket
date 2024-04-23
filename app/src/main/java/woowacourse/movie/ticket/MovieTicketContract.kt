package woowacourse.movie.ticket

interface MovieTicketContract {
    interface View {
        val presenter: MovieTicketPresenter

        fun showTicketInfo(info: Ticket)
    }

    interface Presenter {
        val ticket: Ticket

        fun setTicketInfo()
    }
}
