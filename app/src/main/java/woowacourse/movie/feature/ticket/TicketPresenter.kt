package woowacourse.movie.feature.ticket

class TicketPresenter(
    private val view: TicketContract.View,
    private val ticketData: TicketData,
) : TicketContract.Presenter {
    override fun initTicketView() {
        view.setCancelableMinute(ticketData.cancelableMinute)
        view.setMovieTitle(ticketData.screeningData.title)
        view.setShowTime(ticketData.showtime)
        view.setSeatCodes(getSeatCodes())
        view.setTicketCount(ticketData.ticketCount)
        view.setTicketPrice(ticketData.totalTicketPrice)
    }

    private fun getSeatCodes(): List<String> =
        ticketData.seatsData.toSeatList().map {
            getRowSeatText(it.row.index) + getColSeatText(it.col.index)
        }

    private fun getColSeatText(index: Int) = (index + 1).toString()

    private fun getRowSeatText(index: Int) = ('A'.code + index).toChar().toString()
}
