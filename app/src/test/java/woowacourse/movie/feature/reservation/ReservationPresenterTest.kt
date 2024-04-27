package woowacourse.movie.feature.reservation

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.ScreeningRepository
import woowacourse.movie.domain.TestFixture.MOCK_SCREENING

class ReservationPresenterTest {
    private lateinit var presenter: ReservationPresenter
    private lateinit var view: ReservationContract.View
    private lateinit var screeningRepository: ScreeningRepository

    @BeforeEach
    fun setUp() {
        view = mockk<ReservationContract.View>()
        screeningRepository = mockk<ScreeningRepository>()
        presenter = ReservationPresenter(view)
    }

    @Test
    fun `영화 데이터를 가져와 화면에 표시한다`() {
        // given
        every { screeningRepository.find(any()) } returns MOCK_SCREENING
        every { view.initializeMovieDetails(any()) } just runs
        every { view.setupScreeningSchedulesControls(any()) } just runs

        // when
        presenter.fetchMovieDetails(0)

        // then
        verify { view.initializeMovieDetails(any()) }
        verify { view.setupScreeningSchedulesControls(any()) }
    }

    @Test
    fun `예약이 끝나면 예약 완료 페이지로 이동한다`() {
        // given
        every { view.navigateToCompleteScreen() } just runs

        // when
        presenter.completeSelectSchedule()

        // then
        verify { view.navigateToCompleteScreen() }
    }
}
