package woowacourse.movie.ui.reservation

import io.mockk.called
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.domain.movie.Movie
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class ReservationPresenterTest {
    @MockK
    private lateinit var view: ReservationContract.View

    private lateinit var reservationPresenter: ReservationContract.Presenter

    @BeforeEach
    fun setUp() {
        // view = mockk<ReservationContract.View>()

        reservationPresenter = ReservationPresenter(view)
    }

    @Test
    fun `display movie information`() {
        every { view.showMovie(any()) } just runs
        reservationPresenter.loadMovie()
        verify { view.showMovie(any()) }
    }

    @Test
    fun `예약인원이 1 일때, 플러스 버튼을 누르면 예약 인원이 2 가 된다`() {
        // given
        // every를 왜 쓰는건지..?
        every { view.updateTicketCount(any()) } just runs
        // when
        reservationPresenter.onClickedPlusButton(1)
        // then
        verify { view.updateTicketCount(2) }
    }

    @Test
    fun `예약인원이 99 일때, 플러스 버튼을 누르면 예약 인원이 99 가 된다`() {
        // given
        // every를 왜 쓰는건지..?
        every { view.updateTicketCount(any()) } just runs
        // when
        reservationPresenter.onClickedPlusButton(99)
        // then
        verify { view.updateTicketCount(99) }
        // verify(exactly = 0) { view.updateCount(any()) }
    }

    @Test
    fun `예약인원이 2 일때, 마이너스 버튼을 누르면 예약 인원이 1 이 된다`() {
        // given
        // every를 왜 쓰는건지..?
        every { view.updateTicketCount(any()) } just runs
        // when
        reservationPresenter.onClickedSubButton(2)
        // then
        verify { view.updateTicketCount(1) }
    }

    @Test
    fun `예약인원이 1 일때, 마이너스 버튼을 누르면 예약 인원이 1 이 된다`() {
        // given
        // every를 왜 쓰는건지..?
        every { view.updateTicketCount(any()) } just runs
        // when
        reservationPresenter.onClickedSubButton(1)
        // then
        // verify { view.updateCount(99) }
        // verify(exactly = 0) { view.updateCount(any()) }
        verify { view wasNot called }
    }

    @Test
    fun `예매 완료 버튼을 누르면 예매 내역을 보여준다`() {
        every { view.showTicket(any()) } just runs

        val movie = MOCK_Existing_MOVIE_WITH_ONE_LOCAL_DATE
        val count = 2

        reservationPresenter.onClickedReservation(movie, count)
        // verify { view.showTicket(any()) }
    }

    companion object {
        val MOCK_Existing_MOVIE_WITH_ONE_LOCAL_DATE =
            Movie(
                id = 1,
                title = "mockTitle",
                runningTime = 100,
                screenPeriod = listOf(LocalDate.of(2024, 4, 1)),
                description = "mock description",
                imgResId = 0,
            )
        private val MOCK_Existing_MOVIE_WITH_TWO_LOCAL_DATE =
            Movie(
                id = 1,
                title = "mockTitle",
                runningTime = 100,
                screenPeriod =
                    listOf(
                        LocalDate.of(2024, 4, 1),
                        LocalDate.of(2024, 4, 30),
                    ),
                description = "mock description",
                imgResId = 0,
            )
    }
}
