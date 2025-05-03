package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.scheduler.DefaultScheduler
import woowacourse.movie.fixture.HARRY_POTTER_01
import woowacourse.movie.view.reservation.MovieReservationContract
import woowacourse.movie.view.reservation.MovieReservationPresenter
import woowacourse.movie.view.reservation.model.toUiModel
import java.time.LocalDate

class MovieReservationPresenterTest {
    private lateinit var presenter: MovieReservationContract.Presenter
    private lateinit var view: MovieReservationContract.View

    @BeforeEach
    fun setup() {
        view = mockk(relaxed = true)
        presenter =
            MovieReservationPresenter(view, Ticket(HARRY_POTTER_01).toUiModel(), DefaultScheduler)
    }

    @Test
    fun `영화 정보와 날짜 및 시간 스피너가 초기화된다`() {
        presenter.loadMovieReservationScreen()

        verify { view.showMovieInfo(any()) }
        verify { view.showHeadCount(1) }
        verify { view.updateDateSpinner(any(), any()) }
        verify { view.updateTimeSpinner(any(), any()) }
    }

    @Test
    fun `+ 버튼을 클릭하면 인원 수가 증가하고 화면에 반영된다`() {
        // given
        every { view.showHeadCount(1) } just Runs

        // when
        presenter.onClickIncrementButton()

        // then
        verify { view.showHeadCount(2) }
    }

    @Test
    fun `- 버튼을 클릭하면 인원 수가 감소하고 화면에 반영된다`() {
        presenter.onClickIncrementButton() // 2명 만들고
        presenter.onClickDecrementButton() // 1명으로 감소

        verify(exactly = 2) { view.showHeadCount(any()) }
        verify { view.showHeadCount(1) }
    }

    @Test
    fun `날짜 스피너에서 날짜를 선택하면 선택한 날짜가 반영되고 시간 스피너가 갱신된다`() {
        // given
        val selectedDate = LocalDate.of(2025, 4, 30)

        // when
        presenter.onSelectDate(selectedDate)

        // then
        verify { view.updateTimeSpinner(any(), any()) }
    }

    @Test
    fun `선택 완료 버튼을 클릭하면 예약 완료 화면으로 이동한다`() {
        presenter.completeReservation()
        verify { view.navigateToCompleteScreen(any()) }
    }
}
