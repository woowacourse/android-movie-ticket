package woowacourse.movie.presenter

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.MovieBooked
import woowacourse.movie.fixture.MovieFixture

class MovieBookedPresenterTest {
    private lateinit var view: MovieBooked.View
    private lateinit var presenter: MovieBooked.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MovieBookedPresenter(view)
    }

    @Test
    fun loadMovie_호출시_View의_showMovieInfo가_호출된다() {
        //given - 예약 정보가 주어짐
        val bookingStatus = MovieFixture.BOOKING_STATUS
        every { view.showBookedStatus(any()) } just Runs

        // when - loadBookedStatus를 호출 시
        presenter.loadBookedStatus(bookingStatus)

        // then - showBookedStatus()가 호출된다
        verify {
            view.showBookedStatus(any())
        }
    }
}
