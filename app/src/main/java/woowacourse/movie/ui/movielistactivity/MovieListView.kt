package woowacourse.movie.ui.movielistactivity

import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.model.MovieUIModel.Companion.movieToMovieUiModel
import woowacourse.movie.util.getString
import java.time.LocalDate

class MovieListView(private val view: RecyclerView, clickListener: (MovieUIModel) -> Unit) {

    lateinit var movieListAdapter: MovieListRecyclerAdapter

    init {
        initMovieListAdapter(clickListener)
        initMovieListView()
    }

    private fun initMovieListAdapter(clickListener: (MovieUIModel) -> Unit) {
        // temp Movies 는 서버 어딘가에서 받아오는 것이라 가정
        val tempMovies = List(1000) {
            woowacourse.movie.domain.model.Movie(
                title = "해리 포터와 마법사의 돌$it",
                screeningDay = woowacourse.movie.domain.datetime.ScreeningPeriod(
                    LocalDate.parse("2023-04-01"),
                    LocalDate.parse("2023-04-28")
                ),
                runningTime = 152,
                description = view.getString(R.string.dummy_data)
            )
        }

        val movieUIModels =
            tempMovies.map { movie: woowacourse.movie.domain.model.Movie -> movie.movieToMovieUiModel() }
        movieListAdapter = MovieListRecyclerAdapter(clickListener)
        movieListAdapter.submitList(movieUIModels)
    }

    private fun initMovieListView() {
        view.adapter = movieListAdapter
    }
}
