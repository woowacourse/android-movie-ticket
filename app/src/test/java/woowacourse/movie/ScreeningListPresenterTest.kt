package woowacourse.movie

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.contract.ScreeningListContract
import woowacourse.movie.presenter.ScreeningListPresenter

@ExtendWith(MockKExtension::class)
class ScreeningListPresenterTest {
    @RelaxedMockK
    lateinit var view: ScreeningListContract.View
    @InjectMockKs
    lateinit var presenter: ScreeningListPresenter

    @Test
    fun `상영중인 영화의 리스트를 표기할 수 있어야 한다`() {
        presenter.loadScreenings()
        verify { view.displayScreenings(any()) }
    }

    @Test
    fun `지금 예매 버튼을 누르면 상영 상세 화면으로 넘어가야 한다`() {
        //given
        every { presenter.loadScreenings() } just runs
        //when
        val screeningId = 0
        presenter.selectScreening(screeningId)
        verify { view.navigateToScreeningDetail(screeningId) }
    }
}
