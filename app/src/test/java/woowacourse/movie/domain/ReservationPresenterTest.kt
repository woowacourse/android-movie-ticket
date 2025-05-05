package woowacourse.movie.domain

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.mockk.verifyOrder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.R
import woowacourse.movie.domain.movietime.Date
import woowacourse.movie.view.reservation.detail.ReservationContract
import woowacourse.movie.view.reservation.detail.ReservationPresent
import java.time.LocalDate

class ReservationPresenterTest {
    private lateinit var view: ReservationContract.View
    private lateinit var presenter: ReservationContract.Presenter
    private lateinit var movie: Movie

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = ReservationPresent(view)
        movie =
            Movie(
                R.drawable.harry,
                "해리 포터와 마법사의 돌",
                Date(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
                152,
            )
    }

    @Test
    fun `fetchData 호출 시 뷰에 정확한 값이 표시된다`() {
        // given
        val countSlot = slot<Int>()
        val movieSlot = slot<Movie>()
        val datePositionSlot = slot<Int>()

        every { view.showCount(capture(countSlot)) } just Runs
        every { view.showMovieReservationScreen(capture(movieSlot)) } just Runs
        every { view.showSpinnerData(capture(movieSlot), capture(datePositionSlot)) } just Runs
        every { view.setCountButtons() } just Runs
        every { view.setReservationButton() } just Runs

        // when
        presenter.fetchData(movie)

        // then
        assertThat(countSlot.captured).isEqualTo(1)
        assertThat(movieSlot.captured).isEqualTo(movie)
        assertThat(datePositionSlot.captured).isEqualTo(0)

        verifyOrder {
            view.showCount(1)
            view.showMovieReservationScreen(movie)
            view.setCountButtons()
            view.setReservationButton()
            view.showSpinnerData(movie, 0)
        }
    }

    @Test
    fun `count 증가 시 뷰에 숫자 2가 표시된다`() {
        // given
        val capturedCount = slot<Int>()
        every { view.showCount(capture(capturedCount)) } just Runs
        // when
        presenter.increasedCount()
        // then
        verify { view.showCount(capture(capturedCount)) }
        assertThat(capturedCount.captured).isEqualTo(2)
    }

    @Test
    fun `인원 수 감소 시 count 값이 감소하고 뷰에 반영된다`() {
        // given
        val capturedCounts = mutableListOf<Int>()
        every { view.showCount(capture(capturedCounts)) } just Runs

        // when
        presenter.increasedCount()
        presenter.increasedCount()

        presenter.decreasedCount()

        // then
        verify(exactly = 3) { view.showCount(any()) }
        assertThat(capturedCounts).containsExactly(2, 3, 2)
    }

    @Test
    fun `count가 1일 때 감소 버튼을 눌러도 count는 변하지 않는다`() {
        // given
        val capturedCount = slot<Int>()
        every { view.showCount(capture(capturedCount)) } just Runs

        // when
        presenter.decreasedCount()

        // then
        verify(exactly = 1) { view.showCount(capture(capturedCount)) }
        assertThat(capturedCount.captured).isEqualTo(1)
    }
}
