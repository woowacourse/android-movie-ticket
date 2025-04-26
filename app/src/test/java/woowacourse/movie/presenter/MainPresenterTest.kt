package woowacourse.movie.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import woowacourse.movie.main.Item
import woowacourse.movie.main.MainContract
import woowacourse.movie.main.MainPresenter
import kotlin.test.Test

class MainPresenterTest {
    private lateinit var view: MainContract.View
    private lateinit var presenter: MainPresenter

    @BeforeEach
    fun setUp() {
        view = mockk<MainContract.View>(relaxed = true)
        presenter = MainPresenter(view)
    }

    @Test
    fun `3번째마다 광고를 포함한 리스트를 전달한다`() {
        // when
        presenter.initMovies()

        // then
        verify {
            view.showMovies(
                match {
                    it.withIndex().all { (index, item) ->
                        if ((index + 1) % 4 == 0) {
                            item is Item.AdvertisementItem
                        } else {
                            true
                        }
                    }
                },
            )
        }
    }
}
