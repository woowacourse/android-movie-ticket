package woowacourse.movie.presentation.presenter

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.repository.AdRepository
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.presentation.contract.MainContract
import woowacourse.movie.presentation.view.MainActivity

class MainPresenterTest {
    private lateinit var view: MainContract.View
    private lateinit var presenter: MainPresenterImpl
    private lateinit var movieRepository: MovieRepository
    private lateinit var adRepository: AdRepository

    @BeforeEach
    fun setUp() {
        view = mockk<MainActivity>(relaxed = true)
        movieRepository = mockk(relaxed = true)
        adRepository = mockk(relaxed = true)
        presenter = MainPresenterImpl(movieRepository, adRepository)
    }

    @Test
    fun `View를 설정하면 presenter의 view가 초기화된다`() {
        // when
        presenter.attachView(view)

        // then
        verify { view.onUpdateMovies(any()) }
        verify { view.onUpdateAds(any()) }
    }

    @Test
    fun `View를 detach하면 presenter의 view가 null이 된다`() {
        // given
        presenter.detachView()

        // when
        presenter.loadMovie()
        presenter.loadAds()

        // then
        verify(exactly = 0) { view.onUpdateMovies(any()) }
        verify(exactly = 0) { view.onUpdateAds(any()) }
    }

    @Test
    fun `예약 버튼이 클릭되면 view에서 다음 화면으로 넘어가는 동작을 하도록 presenter가 호출한다`() {
        // given
        presenter.attachView(view)

        // when
        presenter.onReserveButtonClicked(0)

        // then
        verify { view.moveToMovieDetail(any()) }
    }
}
