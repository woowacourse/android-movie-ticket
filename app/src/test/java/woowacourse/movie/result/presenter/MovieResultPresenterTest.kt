package woowacourse.movie.result.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.result.presenter.contract.MovieResultContract

class MovieResultPresenterTest {
    private lateinit var view: MovieResultContract.View
    private lateinit var presenter: MovieResultPresenter

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MovieResultPresenter(view)
        mockkObject(MovieRepository)
    }

    @Test
    fun `loadMovieTicket를 호출하면 예매한 영화 티켓의 정보가 보여진다`() {
        // Given
        every { view.displayMovieTicket(any()) } just runs

        // When
        presenter.loadMovieTicket(0, "2024-04-01", "10:00", 3, "A0, A1, A2")

        // Then
        verify { view.displayMovieTicket(any()) }
    }
}
