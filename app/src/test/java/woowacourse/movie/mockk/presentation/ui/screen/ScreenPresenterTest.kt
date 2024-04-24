package woowacourse.movie.mockk.presentation.ui.screen

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.basic.utils.getDummyScreen
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.presentation.ui.screen.ScreenContract
import woowacourse.movie.presentation.ui.screen.ScreenPresenter

@ExtendWith(MockKExtension::class)
class ScreenPresenterTest {
    @MockK
    private lateinit var view: ScreenContract.View

    private lateinit var presenter: ScreenContract.Presenter

    @MockK
    private lateinit var repository: ScreenRepository

    @BeforeEach
    fun setUp() {
        presenter = ScreenPresenter(view, repository)
    }

    @Test
    fun `ScreenPresenter가 loadScreens()을 했을 때, view에게 screens 데이터를 전달한다`() {
        // given
        every { repository.load() } returns listOf(getDummyScreen())
        every { view.showScreens(any()) } just runs

        // when
        presenter.loadScreens()

        // then
        verify { view.showScreens(listOf(getDummyScreen())) }
    }

    @Test
    fun `ScreenPresenter가 유효한 상영장 id를 통해 onScreenClick을 했을 때, view에게 사영장 id를 전달한다`() {
        // given
        every { view.navigateToDetail(any()) } just runs

        // when
        presenter.onScreenClick(1)

        // then
        verify { view.navigateToDetail(1) }
    }
}
