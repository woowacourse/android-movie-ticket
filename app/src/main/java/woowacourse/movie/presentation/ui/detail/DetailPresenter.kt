package woowacourse.movie.presentation.ui.detail

import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.Ticket.Companion.MAX_TICKET_COUNT
import woowacourse.movie.domain.model.Ticket.Companion.MIN_TICKET_COUNT
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.model.ReservationInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailPresenter(
    private val view: DetailContract.View,
    private val screenRepository: ScreenRepository,
) : DetailContract.Presenter {
    private var _uiModel = DetailUiModel()
    val uiModel: DetailUiModel
        get() = _uiModel

    override fun loadScreen(id: Int) {
        screenRepository.findByScreenId(id = id).onSuccess { screen ->
            _uiModel =
                uiModel.copy(
                    screenId = id,
                    screen = screen,
                    selectedDate = screen.selectableDates.first(),
                    selectedTime = screen.selectableTimes.first(),
                )
            view.showScreen(screen)
            view.showTicket(uiModel.ticket.count)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> {
                    view.showToastMessage(e)
                    view.back()
                }

                else -> {
                    view.showToastMessage(e)
                    view.back()
                }
            }
        }
    }

    override fun registerDate(date: String) {
        _uiModel = uiModel.copy(selectedDate = date)
    }

    override fun registerTime(time: String) {
        _uiModel = uiModel.copy(selectedTime = time)
    }

    override fun updateTicket(count: Int) {
        _uiModel = uiModel.copy(ticket = Ticket(count))
        view.showTicket(count)
    }

    override fun plusTicket() {
        val nextTicket = uiModel.ticket.increase(1)

        if (nextTicket.isInvalidCount()) {
            view.showSnackBar(MessageType.TicketMaxCountMessage(MAX_TICKET_COUNT))
            return
        }
        _uiModel = uiModel.copy(ticket = nextTicket)
        view.showTicket(uiModel.ticket.count)
    }

    override fun minusTicket() {
        val nextTicket = uiModel.ticket.decrease(1)

        if (nextTicket.isInvalidCount()) {
            view.showSnackBar(MessageType.TicketMinCountMessage(MIN_TICKET_COUNT))
            return
        }
        _uiModel = uiModel.copy(ticket = nextTicket)
        view.showTicket(uiModel.ticket.count)
    }

    override fun selectSeat() {
        val dateString = "${uiModel.selectedDate}T${uiModel.selectedTime}:00"
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val parsedDateTime = LocalDateTime.parse(dateString, formatter)!!
        val reservationInfo =
            ReservationInfo(
                screenId = uiModel.screenId,
                dateTime = parsedDateTime,
                ticketCount = uiModel.ticket.count,
            )
        view.navigateToSeatSelection(reservationInfo)
    }
}
