package woowacourse.movie.feature.main

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.ScreeningRepository
import woowacourse.movie.domain.TestFixture.MOCK_SCREENING
import woowacourse.movie.feature.main.ui.toUiModel

class MainPresenterTest {
    private lateinit var presenter: MainPresenter
    private lateinit var view: MainContract.View
    private lateinit var repository: ScreeningRepository

    @BeforeEach
    fun setup() {
        view = mockk<MainContract.View>()
        repository = mockk<ScreeningRepository>()
        presenter = MainPresenter(view, repository)
    }

    @Test
    fun `영화 리스트를 불러와 뷰에 보여준다`() {
        // given
        every { repository.findAll() } returns listOf(MOCK_SCREENING)
        every { view.displayMovies(any()) } just runs
        // when
        presenter.fetchMovieList()
        // Then
        verify { view.displayMovies(listOf(MOCK_SCREENING).map { it.toUiModel() }) }
    }

    @Test
    fun `영화를 클릭하면 예약 화면으로 이동한다`() {
        // given
        every { view.navigateToReservationScreen(any()) } just runs
        // when
        presenter.selectMovie(0)
        // Then
        verify { view.navigateToReservationScreen(0) }
    }
}
