package woowacourse.movie.basic.presentation.ui.screen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.basic.presentation.fakerepository.FakeScreenRepository
import woowacourse.movie.basic.presentation.ui.screen.fake.FakeScreenView
import woowacourse.movie.basic.utils.getDummyScreen
import woowacourse.movie.domain.model.ScreenView.Screen
import woowacourse.movie.presentation.ui.screen.ScreenPresenter

class ScreenPresenterTest {
    private lateinit var screens: List<Screen>
    private lateinit var view: FakeScreenView
    private lateinit var presenter: ScreenPresenter

    @BeforeEach
    fun setUp() {
        screens = listOf(getDummyScreen())
        view = FakeScreenView()
        val repository = FakeScreenRepository(screens)
        presenter = ScreenPresenter(view, repository)
    }

    @Test
    fun `ScreenPresenter가 loadScreens()을 했을 때, view에게 screens 데이터를 전달한다`() {
        // Given & When
        presenter.loadScreens()

        // Then
        assertThat(view.screens).isEqualTo(screens)
    }

    @Test
    fun `ScreenPresenter가 유효한 상영장 id를 통해 onScreenClick을 했을 때, view에게 사영장 id를 전달한다`() {
        // Given
        val id = screens.first().id

        // When
        presenter.onScreenClick(screens.first().id)

        // Then
        assertThat(view.detailScreenId).isEqualTo(id)
    }
}
