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
        every { view.showMovieDetails(any()) } just runs
        every { view.showScreeningSchedules(any()) } just runs

        // when
        presenter.fetchScreeningDetails(0)

        // then
        verify { view.showMovieDetails(any()) }
        verify { view.showScreeningSchedules(any()) }
    }
}
