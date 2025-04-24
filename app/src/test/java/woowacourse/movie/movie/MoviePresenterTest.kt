package woowacourse.movie.movie

import android.content.Intent
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Movie
import java.time.LocalDate

class MoviePresenterTest {
    private lateinit var presenter: MoviePresenter
    private lateinit var mockView: MovieContract.View
    private lateinit var mockMovieList: List<Movie>

    @BeforeEach
    fun setUp() {
        mockView = mockk(relaxed = true)
        mockMovieList =
            listOf(
                Movie(
                    title = "해리 포터와 마법사의 돌",
                    imageSource = "harry_potter.png",
                    screeningStartDate = LocalDate.of(2025, 4, 1),
                    screeningEndDate = LocalDate.of(2025, 4, 25),
                    runningTime = 152,
                ),
                Movie(
                    title = "스타 이즈 본",
                    imageSource = "star_is_born.jpg",
                    screeningStartDate = LocalDate.of(2025, 4, 19),
                    screeningEndDate = LocalDate.of(2025, 5, 25),
                    runningTime = 135,
                ),
            )

        presenter = MoviePresenter(view = mockView)
    }

    @Test
    fun `영화 리스트가 null이면 기본 메시지를 띄운다`() {
        val emptyIntent = Intent()

        presenter.initializeData(emptyIntent)

        verify { mockView.showToast("기본 영화 목록을 불러왔습니다.") }
        verify {
            mockView.showMovies(
                match {
                    it.size == 2 &&
                        it[0].title == "해리 포터와 마법사의 돌" &&
                        it[1].title == "스타 이즈 본"
                },
            )
        }
    }

    @Test
    fun `지금 예매 버튼을 누르면 다음 화면으로 넘어간다`() {
        val movie = presenter.getMockMovieList().first()

        presenter.onReserveClicked(movie)

        verify { mockView.startBookingActivity(movie) }
    }
}
