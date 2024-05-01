package woowacourse.movie.presentation.main

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
import woowacourse.movie.MOVIE_ITEM
import woowacourse.movie.domain.MovieRepository

@ExtendWith(MockKExtension::class)
class MovieMainPresenterTest {
    @MockK
    private lateinit var mainContractView: MovieMainContract.View

    @MockK
    private lateinit var movieRepository: MovieRepository

    private lateinit var mainPresenter: MovieMainPresenter

    @BeforeEach
    fun setUp() {
        mainPresenter = MovieMainPresenter(mainContractView, movieRepository)
    }

    @Test
    fun `loadMovies를 통해 initView에 데이터가 전달된다`() {
        every { movieRepository.findAllMovies() } returns listOf(MOVIE)
        every { mainContractView.onInitView(any()) } just runs

        mainPresenter.loadMovies()
        verify { mainContractView.onInitView(listOf(MOVIE_ITEM)) }
    }
}
