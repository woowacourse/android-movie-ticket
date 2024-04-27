package woowacourse.movie.presentation.result

import io.kotest.assertions.any
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import woowacourse.movie.MOVIE
import woowacourse.movie.SCREEN_DATE_TIME
import woowacourse.movie.SEAT
import woowacourse.movie.domain.MovieRepository

@ExtendWith(MockKExtension::class)
class MovieResultPresenterTest {
    @MockK
    private lateinit var resultContractView: MovieResultContract.View

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var resultPresenter: MovieResultPresenter

    @BeforeEach
    fun setUp() {
        resultPresenter = MovieResultPresenter(resultContractView, movieRepository)
    }

    @Test
    fun `loadResult()로 얻은 데이터를 onUpdateView를 통해 view에 반영한다`() {
        every { movieRepository.findMovieById(any()) } returns MOVIE
        every { movieRepository.findScreenDateTimeByMovieScreenDateTimeId(any()) } returns SCREEN_DATE_TIME
        every { movieRepository.findSeatById(any()) } returns SEAT
        every { resultContractView.onError(any()) } just runs
        every { resultContractView.onUpdateView(any()) } just runs

        resultPresenter.loadResult(0, 0, listOf(0), 1)

        verify(exactly = 1) { resultContractView.onUpdateView(any()) }
        verify(exactly = 0) { resultContractView.onError(any()) }
    }

    @Test
    fun `loadResult()로 얻은 데이터가 없을 경우 onError()를 실행한다`() {
        every { movieRepository.findMovieById(any()) } returns null
        every { movieRepository.findScreenDateTimeByMovieScreenDateTimeId(any()) } returns null
        every { movieRepository.findSeatById(any()) } returns null
        every { resultContractView.onError(any()) } just runs
        every { resultContractView.onUpdateView(any()) } just runs

        resultPresenter.loadResult(0, 0, listOf(0), 1)

        verify(exactly = 0) { resultContractView.onUpdateView(any()) }
        verify(exactly = 1) { resultContractView.onError(any()) }
    }
}
