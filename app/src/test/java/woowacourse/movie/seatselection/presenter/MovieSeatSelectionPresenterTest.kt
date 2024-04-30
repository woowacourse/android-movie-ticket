package woowacourse.movie.seatselection.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.R
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.seatselection.presenter.contract.MovieSeatSelectionContract

class MovieSeatSelectionPresenterTest {
    private lateinit var view: MovieSeatSelectionContract.View
    private lateinit var presenter: MovieSeatSelectionPresenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MovieSeatSelectionPresenter(view)
        presenter.updateSelectedSeats(3)
        mockkObject(MovieRepository)
    }

    @Test
    fun `loadMovieTitle를 호출하면 선택된 영화의 제목이 보여진다`() {
        // Given
        every { view.displayMovieTitle(any()) } just runs

        // When
        presenter.loadMovieTitle(0)

        // Then
        verify { view.displayMovieTitle("타이타닉 0") }
    }

    @Test
    fun `loadTableSeats를 호출하면 테이블 좌석들의 정보가 세팅된다`() {
        // Given
        every { view.setUpTableSeats(any()) } just runs

        // When
        presenter.loadTableSeats(3)

        // Then
        verify { view.setUpTableSeats(any()) }
    }

    @Test
    fun `clickTableSeat를 호출하면 좌석의 배경색이 바뀌고 선택 결과가 화면에 보여진다`() {
        // Given
        every { view.updateSeatBackgroundColor(any(), any()) } just runs
        every { view.updateSelectResult(any()) } just runs

        // When
        presenter.clickTableSeat(0)

        // Then
        verify { view.updateSeatBackgroundColor(0, R.color.selected) }
        verify { view.updateSelectResult(any()) }
    }

    @Test
    fun `clickPositiveButton를 예매 결과를 보여주는 화면으로 이동한다`() {
        // Given
        every { view.navigateToResultView(any()) } just runs

        // When

        presenter.clickPositiveButton()

        // Then
        verify { view.navigateToResultView(any()) }
    }
}
