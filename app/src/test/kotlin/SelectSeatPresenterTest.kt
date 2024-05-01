import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import woowacourse.movie.data.DummyMovies
import woowacourse.movie.model.SeatRate
import woowacourse.movie.selectseat.SelectSeatContract
import woowacourse.movie.selectseat.SelectSeatPresenter
import woowacourse.movie.selectseat.uimodel.PriceUiModel
import woowacourse.movie.selectseat.uimodel.SeatUiModel

class SelectSeatPresenterTest {
    private lateinit var view: SelectSeatContract.View

    private lateinit var presenter: SelectSeatContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<SelectSeatContract.View>()
        presenter = SelectSeatPresenter(view, DummyMovies)
    }

    @Test
    @DisplayName("극장의 좌석 정보를 불러오면 화면에 나타난다")
    fun show_Seat_view_When_load_data_Success() {
        // when
        every { view.showSeat(any()) } just Runs

        // given
        presenter.loadSeat(1)

        // then
        verify(exactly = 1) { view.showSeat(any()) }
    }

    @Test
    @DisplayName("선택된 좌석들의 예매 가격을 계산하면 뷰에 반영한다.")
    fun update_view_When_complete_selected_seats_price() {
        // when
        every { view.updatePrice(PriceUiModel(15_000)) } just Runs

        // given
        presenter.calculatePrice(listOf(SeatUiModel(1, 1, SeatRate.S)))

        // then
        verify { view.updatePrice(PriceUiModel(15_000)) }
    }
}
