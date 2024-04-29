package woowacourse.movie.mockk.presentation.ui.screen

import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import woowacourse.movie.basic.utils.getDummyScreen
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.presentation.ui.screen.ScreenContract
import woowacourse.movie.presentation.ui.screen.ScreenPresenter

class ScreenPresenterBehaviorSpec : BehaviorSpec({
    val view: ScreenContract.View = mockk()

    val repository: ScreenRepository = mockk()

    @InjectMockKs
    val presenter: ScreenContract.Presenter = ScreenPresenter(view, repository)

    Given("영화 스크린 정보가 있을 때") {
        every { repository.load() } returns listOf(getDummyScreen())
        every { view.showScreens(any()) } just runs

        When("ScreenPresenter가 loadScreens()을 하면") {
            presenter.loadScreens()

            Then("view에게 screens 데이터를 전달한다") {
                verify { view.showScreens(listOf(getDummyScreen())) }
            }
        }
    }

    Given("유효한 상영장 id가 있을 때") {
        every { view.navigateToDetail(any()) } just runs

        When("ScreenPresenter가 onScreenClick()을 한다면") {
            presenter.onScreenClick(1)

            Then("view에게 사영장 id를 전달한다") {
                verify { view.navigateToDetail(1) }
            }
        }
    }
})
