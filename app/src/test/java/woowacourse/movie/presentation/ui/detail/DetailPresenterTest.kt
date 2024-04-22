package woowacourse.movie.presentation.ui.detail

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.presentation.fakerepository.FakeReservationRepository
import woowacourse.movie.presentation.fakerepository.FakeScreenRepository
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.ui.detail.fake.FakeDetailView
import woowacourse.movie.presentation.utils.getDummyReservation
import woowacourse.movie.presentation.utils.getDummyScreen

class DetailPresenterTest {
    private lateinit var screens: List<Screen>
    private lateinit var reservation: Reservation
    private lateinit var view: FakeDetailView
    private lateinit var presenter: DetailPresenter

    @BeforeEach
    fun setUp() {
        screens = listOf(getDummyScreen())
        reservation = getDummyReservation()
        view = FakeDetailView()
        val reservationRepository = FakeReservationRepository(reservation.id, reservation)
        val screenRepository = FakeScreenRepository(screens)
        presenter = DetailPresenter(view, screenRepository, reservationRepository)
    }

    @Test
    fun `DetailPresenter가 유효한 상영 id값으로 loadScreen()을 했을 때, view에게 screen, count 정보를 전달한다`() {
        // Given & When
        presenter.loadScreen(screens.first().id)

        // Then
        assertThat(view.screen).isEqualTo(screens.first())
        assertThat(view.ticketCount).isEqualTo(1)
    }

    @Test
    fun `DetailPresenter가 유효하지 않은 상영 id값으로 loadScreen()을 했을 때, view에게 back과 throwable를 전달한다`() {
        // Given & When
        presenter.loadScreen(screens.first().id + 1)

        // Then
        assertThat(view.isBack).isTrue()
        assertThat(view.throwable).isInstanceOf(NoSuchElementException::class.java)
    }

    @Test
    fun `DetailPresenter가 ticket 값이 1일 때 plusTicket()을 하면, view에게 티켓 개수를 전달한다`() {
        // Given & When
        presenter.plusTicket()

        // Then
        assertThat(view.ticketCount).isEqualTo(2)
    }

    @Test
    fun `DetailPresenter가 ticket 값이 티켓의 최대 개수일 때 plusTicket()을 하면, view에게 snackbar message(TicketMaxCountMessage)를 전달한다`() {
        // Given & When
        repeat(Ticket.MAX_TICKET_COUNT) {
            presenter.plusTicket()
        }
        presenter.plusTicket()

        // Then
        assertThat(view.snackBarMessage).isEqualTo(MessageType.TicketMaxCountMessage(Ticket.MAX_TICKET_COUNT))
    }

    @Test
    fun `DetailPresenter가 ticket 값이 1일 때 minusTicket()을 하면, view에게 snackbar message(TicketMinCountMessage)를 전달한다`() {
        // Given & When
        presenter.minusTicket()

        // Then
        assertThat(view.snackBarMessage).isEqualTo(MessageType.TicketMinCountMessage(Ticket.MIN_TICKET_COUNT))
    }

    @Test
    fun `DetailPresenter가 ticket 값이 티켓의 최대 개수일 때 minusTicket()을 하면, view에게 티켓 개수를 전달한다`() {
        // Given & When
        repeat(Ticket.MAX_TICKET_COUNT) {
            presenter.plusTicket()
        }
        presenter.minusTicket()

        // Then
        assertThat(view.ticketCount).isEqualTo(Ticket.MAX_TICKET_COUNT - 1)
    }
}
