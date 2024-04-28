package woowacourse.movie.presenter

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.contract.SeatSelectionContract
import woowacourse.movie.repository.TheaterRepository

@ExtendWith(MockKExtension::class)
class SeatSelectionPresenterTest {

    @RelaxedMockK
    lateinit var view: SeatSelectionContract.View

    @RelaxedMockK
    lateinit var theaterRepository: TheaterRepository

    @InjectMockKs
    lateinit var presenter: SeatSelectionPresenter

    @Test
    fun `좌석을 표기할 수 있어야 한다`() {
        presenter = SeatSelectionPresenter(view, theaterRepository)
        presenter.loadTheater()
        verify {
            view.displayTheater(any())
        }
    }
}
