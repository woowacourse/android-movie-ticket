package woowacourse.movie.home

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.db.MediaContentsDB

class HomePresenterTest {
    private lateinit var view: HomeContract.View
    private lateinit var presenter: HomeContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<HomeContract.View>()

        every { view.showMediaContents(any()) } just runs

        presenter = HomePresenter(view)
    }

    @Test
    fun `영화와 광고 리스트를 표시한다`() {
        every { view.showMediaContents(MediaContentsDB.obtainMediaContents()) } just runs

        presenter.loadMediaContents()

        verify { view.showMediaContents(MediaContentsDB.obtainMediaContents()) }
    }
}
