package woowacourse.movie.presentation.ui.screen.adapter

import android.view.LayoutInflater
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.presentation.ui.screen.ScreenActivity
import woowacourse.movie.presentation.ui.screen.ScreenContract
import woowacourse.movie.presentation.ui.screen.ScreenPresenter
import woowacourse.movie.presentation.ui.screen.adapter.ScreenRecyclerViewViewHolder.ScreenViewHolder
import woowacourse.movie.presentation.ui.screen.fake.FakeScreenView
import woowacourse.movie.presentation.utils.getDummyScreen

@RunWith(AndroidJUnit4::class)
class ScreenViewHolderTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<ScreenActivity> =
        ActivityScenarioRule(ScreenActivity::class.java)

    private lateinit var viewHolder: ScreenViewHolder
    private lateinit var presenter: ScreenContract.Presenter
    private lateinit var fakeScreenView: FakeScreenView

    @Before
    fun setUp() {
        val view =
            LayoutInflater.from(InstrumentationRegistry.getInstrumentation().targetContext)
                .inflate(R.layout.holder_screen, null)
        fakeScreenView = FakeScreenView()
        presenter = ScreenPresenter(fakeScreenView, DummyScreens())
        viewHolder = ScreenViewHolder(view, presenter)
    }

    @Test
    fun `올바른_스크린을_바인딩_할_때_영화_제목이_해당_데이터로_업데이트된다`() {
        val screen = getDummyScreen()
        viewHolder.bind(screen)
        assertEquals(screen.movie.title, viewHolder.title.text)
    }

    @Test
    fun `리사이클러뷰의_스크린_아이템을_클릭했을_때_스크린ID_값을_View에게_전달한다`() {
        // Given
        val screen = getDummyScreen()
        viewHolder.bind(screen)

        // when
        presenter.onScreenClick(screen.id)

        // then
        assertEquals(screen.id, fakeScreenView.detailScreenId)
    }
}
