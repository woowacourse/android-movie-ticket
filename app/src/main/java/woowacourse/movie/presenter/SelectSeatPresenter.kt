package woowacourse.movie.presenter

import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.model.TicketData
import woowacourse.movie.view.selectSeat.SelectSeatView

class SelectSeatPresenter(
    private val view: SelectSeatView,
) {
    private val screeningData: ScreeningData by lazy { ticketDataEmptySeat.screeningData }
    private val screening: Screening by lazy { screeningData.toScreening() }
    private val ticketDataEmptySeat: TicketData by lazy {
        view.getTicketData()
    }

//    fun initTicketData(
//        savedTicketCount: Int,
//        savedTimeItemPosition: Int,
//    ) {
//        ticketCount = TicketCount.create(savedTicketCount).getOrDefault()
//        timeItemPosition = savedTimeItemPosition
//    }

    fun initSelectSeatUI() {
        view.initMovieTitleUI(ticketDataEmptySeat)

//        view.initScreeningInfoUI(screeningData)
//        view.setDateSelectUi(screening)
//        view.setTimeSelectUi(screening.period.start, screening, timeItemPosition)
//        view.setTicketCounterUi(ticketCount)
    }

    fun navigateToTicketUI() {
        view.navigateToTicketUI(ticketDataEmptySeat) // TODO: 좌석정보 추가 필요
    }

//    companion object {
//        private const val ERROR_NOT_SELECTED_DATETIME = "예매 정보가 선택되지 않았습니다"
//    }
}
