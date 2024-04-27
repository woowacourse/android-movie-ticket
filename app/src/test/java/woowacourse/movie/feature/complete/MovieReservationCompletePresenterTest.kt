package woowacourse.movie.feature.complete

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.data.MovieRepository
import woowacourse.movie.model.data.MovieRepositoryImpl

class MovieReservationCompletePresenterTest {
    private lateinit var view: MovieReservationCompleteContract.View
    private val repository: MovieRepository = MovieRepositoryImpl
    private lateinit var presenter: MovieReservationCompletePresenter

    @BeforeEach
    fun setUp() {
        view = mockk<MovieReservationCompleteContract.View>()
        presenter = MovieReservationCompletePresenter(view, MovieRepositoryImpl)
    }

    @Test
    fun `영화 데이터를 불러오면 영화 예매 완료 뷰가 초기화된다`() {
        // given
        val movieId = 0L
        every { view.initializeReservationCompleteView(any()) } just runs

        // when
        presenter.loadMovieData(movieId)

        // then
        verify { view.initializeReservationCompleteView(repository.find(movieId))}
    }
}
