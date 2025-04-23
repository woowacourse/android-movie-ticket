package woowacourse.movie.presentation.movies

import io.mockk.Runs
import io.mockk.every
import io.mockk.invoke
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.presentation.fixture.dummyMovie
import woowacourse.movie.presentation.view.movies.MoviesContract
import woowacourse.movie.presentation.view.movies.MoviesPresenter

class MoviesPresenterTest {
    private lateinit var presenter: MoviesContract.Presenter
    private lateinit var view: MoviesContract.View

    @BeforeEach
    fun setUp() {
        view = mockk()
        presenter = MoviesPresenter(view)
    }

    @Test
    fun `영화 목록을 불러온다`() {
        // given - Just Runs
        every { view.setScreen(any(), any()) } just Runs

        // when - 영화 목록 조회
        presenter.fetchData()

        // then - 조회 후 뷰에 반영 함수 호출 여부 검증
        verify { view.setScreen(any(), any()) }
    }

    @Test
    fun `영화 예매 클릭 시 예약 화면으로 이동한다`() {
        // given - 화면에 표시된 영화 아이템이 클릭되었다고 가정
        every { view.setScreen(any(), captureLambda()) } answers {
            lambda<(Movie) -> Unit>().invoke(dummyMovie)
        }

        // given - Just Runs
        every { view.navigateToReservationScreen(any()) } just Runs

        // when - 영화 목록 조회
        presenter.fetchData()

        // then - 예매 화면 이동 함수 호출 여부 검증
        verify { view.navigateToReservationScreen(dummyMovie) }
    }
}
