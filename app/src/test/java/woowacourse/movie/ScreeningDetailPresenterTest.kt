package woowacourse.movie

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import net.bytebuddy.matcher.ElementMatchers.any
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.contract.ScreeningDetailContract
import woowacourse.movie.presenter.ScreeningDetailPresenter

@ExtendWith(MockKExtension::class)
class ScreeningDetailPresenterTest {
    @RelaxedMockK
    lateinit var view: ScreeningDetailContract.View
    lateinit var presenter: ScreeningDetailContract.Presenter

    @BeforeEach
    fun setUp() {
        presenter = ScreeningDetailPresenter(view)
    }

    @Test
    fun `영화의 상세 정보를 표시할 수 있어야 한다`() {
        every {view.displayScreening(any())}
        verify{
            presenter.loadScreening()
        }
    }
}
