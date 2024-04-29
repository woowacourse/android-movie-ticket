package woowacourse.movie.basic.presentation.ui.reservation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.basic.presentation.fakerepository.FakeReservationRepository
import woowacourse.movie.basic.presentation.ui.reservation.fake.FakeReservationView
import woowacourse.movie.basic.utils.getDummyReservation
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.presentation.ui.reservation.ReservationPresenter

class ReservationPresenterTest {
    private lateinit var reservation: Reservation
    private lateinit var view: FakeReservationView
    private lateinit var presenter: ReservationPresenter

    @BeforeEach
    fun setUp() {
        reservation = getDummyReservation()
        view = FakeReservationView()
        val repository = FakeReservationRepository(reservation.id, reservation)
        presenter = ReservationPresenter(view, repository)
    }

    @Test
    fun `ReservationPresenter가 유효한 예매 id를 통해 loadReservation()을 했을 때, view에게 reservation 데이터를 전달한다`() {
        // Given & When
        presenter.loadReservation(reservation.id)

        // Then
        assertThat(view.reservation).isEqualTo(reservation)
    }

    @Test
    fun `ScreenPresenter가 유효하지 않은 예매 id를 통해 loadReservation()했을 때, view에게 back과 throwable를 전달한다`() {
        // Given & When
        presenter.loadReservation(reservation.id + 1)

        // Then
        assertThat(view.isBack).isTrue()
        assertThat(view.throwable).isInstanceOf(NoSuchElementException::class.java)
    }
}
