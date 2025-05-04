package woowacourse.movie.presentation.seats

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.movie.MovieTicket
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatPosition
import java.time.LocalDateTime

class SeatsPresenterTest {
    private lateinit var view: SeatsContract.View
    private lateinit var presenter: SeatsContract.Presenter
    private val movieTicket =
        MovieTicket(
            "Test",
            LocalDateTime.of(2025, 12, 31, 12, 0),
            1,
        )

    @BeforeEach
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = SeatsPresenter(view)
        presenter.loadSeats(movieTicket)
    }

    @Test
    fun `좌석, 영화 제목, 금액이 출력된다`() {
        // Then
        verify { view.initSeats() }
        verify { view.showMovieTitle(movieTicket.title) }
        verify { view.updateAmount(movieTicket.amount) }
    }

    @Test
    fun `좌석을 선택하면 좌석을 추가하고 금액을 갱신한다`() {
        // Given
        val seat = SeatPosition(1, 1)

        // When
        presenter.selectSeat(seat)

        // Then
        verify { view.updateAmount(10000) }
    }

    @Test
    fun `선택한 좌석을 재선택하면 좌석을 제거하고 금액을 갱신한다`() {
        // Given
        val seat = SeatPosition(1, 1)
        presenter.selectSeat(seat)

        // When
        presenter.selectSeat(seat)

        // Then
        verify { view.updateAmount(0) }
    }

    @Test
    fun `인원수가 충족됐을 때 좌석을 선택할 경우 좌석을 추가하지 않고 토스트를 출력한다`() {
        // Given
        val seat1 = SeatPosition(1, 1)
        val seat2 = SeatPosition(2, 2)

        // When
        presenter.selectSeat(seat1)
        presenter.selectSeat(seat2)

        // Then
        verify { view.showToast("좌석은 1개만 선택할 수 있습니다.") }
    }

    @Test
    fun `예매 버튼을 누르면 티켓을 생성하고 화면을 이동한다`() {
        // Given
        val seat = SeatPosition(1, 1)
        presenter.selectSeat(seat)

        // When
        presenter.publishMovieTicket()

        // Then
        verify {
            view.navigateToSummary(
                match {
                    it.title == movieTicket.title &&
                        it.screeningDateTime == movieTicket.screeningDateTime &&
                        it.headCount == movieTicket.headCount &&
                        it.seats == listOf(Seat(seat)) &&
                        it.amount == 10000
                },
            )
        }
    }
}
