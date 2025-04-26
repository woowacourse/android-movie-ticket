package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.MovieBooking
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.fixture.MovieFixture
import java.time.LocalDate

class MovieBookingPresenterTest {
    private lateinit var view: MovieBooking.View
    private lateinit var presenter: MovieBooking.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MovieBookingPresenter(view)
    }

    @Test
    fun loadMovie_호출시_View의_showMovieInfo가_호출된다() {
        //given - 영화 정보가 주어짐
        val movie = MovieFixture.movie.copy(
            screeningPeriod = ScreeningPeriod(
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2)
            )
        )

        every { view.showMovieInfo() } just Runs
        every { view.updateMemberCount(any()) } just Runs
        every { view.showBookingDate(any()) } just Runs
        every { view.showBookingTimes(any()) } just Runs

        // when - loadMovie를 호출 시
        presenter.loadMovie(movie)

        // then - showMovieInfo()가 호출된다
        verifyAll {
            view.showMovieInfo()
            view.updateMemberCount(any())
            view.showBookingDate(any())
            view.showBookingTimes(any())
        }
    }

    @Test
    fun increaseCount를_호출시_updateMemberCount를_호출한다() {
        // given - count가 주어짐
        every { view.updateMemberCount(any()) } just Runs

        // when - increasesCount()를 호출 시
        presenter.increaseCount()

        // then - updateMemberCount(count)가 호출된다
        verify { view.updateMemberCount(any()) }
    }

    @Test
    fun decreaseCount_1명일_때_에러를_보여준다() {
        // given
        every { view.showError(any()) } just Runs
        every { view.updateMemberCount(any()) } just Runs

        // when
        presenter.decreaseCount()

        // then
        verify {
            view.showError(R.string.error_people_over_one)
        }
        verify(exactly = 0) {
            view.updateMemberCount(any())
        }
    }

    @Test
    fun decreaseCount_2명이상일_때_인원수가_감소한다() {
        // given
        every { view.showError(any()) } just Runs
        every { view.updateMemberCount(any()) } just Runs

        presenter.increaseCount()

        // when
        presenter.decreaseCount()

        // then
        verify(exactly = 0) {
            view.showError(any())
        }
        verify {
            view.updateMemberCount(1)
        }
    }

    @Test
    fun 날짜를_고르면_예약가능한_시간을_보여준다() {
        // given
        every { view.showBookingTimes(any()) } just Runs

        // when
        presenter.selectDate(LocalDate.now())

        // then
        verify {
            view.showBookingTimes(any())
        }
    }
}
