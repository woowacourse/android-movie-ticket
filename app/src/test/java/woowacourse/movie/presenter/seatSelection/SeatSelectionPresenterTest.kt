package woowacourse.movie.presenter.seatSelection

import io.kotest.core.spec.style.AnnotationSpec.After
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.presenter.MOVIE_TO_RESERVE
import woowacourse.movie.presenter.SEAT_2_2
import woowacourse.movie.presenter.SEAT_2_3

class SeatSelectionPresenterTest {
    private lateinit var presenter: SeatSelectionPresenter
    private lateinit var view: SeatSelectionContracts.View

    @BeforeEach
    fun setup() {
        view = mockk()
        presenter = SeatSelectionPresenter(view)
    }

    @Test
    fun `좌석들을 가져오면 좌석들이 보인다`() {
        // given:
        every { view.showSeats(any()) } just Runs

        // when:
        presenter.loadSeats(5, 4)

        // then:
        verify { view.showSeats(any()) }
    }

    @Test
    fun `예매할 영화 정보를 업데이트하면 영화 제목, 가격이 보이고 확인 버튼이 비활성화 된다`() {
        // given:
        every { view.showMovieTitle(any()) } just Runs
        every { view.showPrice(any()) } just Runs
        every { view.showButtonEnabled(false) } just Runs

        // when:
        presenter.updateMovieToReserve(MOVIE_TO_RESERVE)

        // then:
        every { view.showMovieTitle(any()) } just Runs
        every { view.showPrice(any()) } just Runs
        every { view.showButtonEnabled(false) } just Runs
    }

    @Test
    fun `선택한 좌석을 업데이트 하면 좌석과 버튼의 활성화 여부가 업데이트되고 총 가격이 보인다`() {
        // given:
        every { view.showMovieTitle(any()) } just Runs
        every { view.showPrice(any()) } just Runs
        every { view.showButtonEnabled(any()) } just Runs
        every { view.updateSeatsEnabled(any()) } just Runs
        every { view.showButtonEnabled(any()) } just Runs
        every { view.showPrice(any()) } just Runs

        presenter.updateMovieToReserve(MOVIE_TO_RESERVE)

        // when:
        presenter.updateSelectedSeat(SEAT_2_3)

        // then:
        verify { view.updateSeatsEnabled(any()) }
        verify { view.showButtonEnabled(any()) }
        verify { view.showPrice(any()) }
    }

    @Test
    fun `선택한 좌석을 재선택하면 좌석과 버튼의 활성화 여부가 업데이트되고 총 가격이 보인다`() {
        // given:
        every { view.showMovieTitle(any()) } just Runs
        every { view.showPrice(any()) } just Runs
        every { view.showButtonEnabled(any()) } just Runs
        every { view.updateSeatsEnabled(any()) } just Runs
        every { view.showButtonEnabled(any()) } just Runs
        every { view.showPrice(any()) } just Runs

        presenter.updateMovieToReserve(MOVIE_TO_RESERVE)

        presenter.updateSelectedSeat(SEAT_2_3) // 선택

        // when:
        presenter.updateSelectedSeat(SEAT_2_3) // 재선택

        // then:
        verify { view.updateSeatsEnabled(any()) }
        verify { view.showButtonEnabled(any()) }
        verify { view.showPrice(any()) }
    }

    @Test
    fun `구매할 티켓 개수만큼 좌석을 선택하면, 좌석 선택 활성화 함수가 false로 호출된다`() {
        // given:
        every { view.showMovieTitle(any()) } just Runs
        every { view.showPrice(any()) } just Runs
        every { view.showButtonEnabled(any()) } just Runs
        every { view.updateSeatsEnabled(any()) } just Runs
        every { view.showButtonEnabled(any()) } just Runs
        every { view.showPrice(any()) } just Runs

        presenter.updateMovieToReserve(MOVIE_TO_RESERVE)

        // when:
        presenter.updateSelectedSeat(SEAT_2_3) // 선택
        presenter.updateSelectedSeat(SEAT_2_2) // 선택

        // then:
        verify { view.updateSeatsEnabled(false) }
        verify { view.showButtonEnabled(any()) }
        verify { view.showPrice(any()) }
    }

    @Test
    fun `예매 완료 요청이 오면 예매 완료 뷰가 보인다`() {
        // given:
        every { view.showMovieTitle(any()) } just Runs
        every { view.showPrice(any()) } just Runs
        every { view.showButtonEnabled(false) } just Runs
        every { view.showReservationCompleteView(any()) } just Runs

        presenter.updateMovieToReserve(MOVIE_TO_RESERVE)

        // when:
        presenter.onReservationCompleteRequest()

        // then:
        verify { view.showReservationCompleteView(any()) }
    }

    @After
    fun finish() {
        clearAllMocks()
    }
}
