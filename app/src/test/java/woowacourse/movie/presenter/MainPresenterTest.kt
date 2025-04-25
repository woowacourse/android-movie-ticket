package woowacourse.movie.presenter

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Test
import woowacourse.movie.activity.MainContract
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.global.ServiceLocator

class MainPresenterTest {
    private val view = mockk<MainContract.View>()
    private val presenter = ServiceLocator.mainPresenter(view)

    @Test
    fun `moviesDto를 반환한다`() {
        every { view.initMovieDto(any()) } just runs
        presenter.initMovieDto()
        verify {
            view.initMovieDto(
                ServiceLocator.movies.map { MovieDto.fromMovie(it) },
            )
        }
    }
}
