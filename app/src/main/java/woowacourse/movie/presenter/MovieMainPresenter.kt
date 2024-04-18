package woowacourse.movie.presenter

import android.content.Context
import woowacourse.movie.model.MovieAdapter
import woowacourse.movie.model.MovieRepository

class MovieMainPresenter(private val movieChoiceContractView: MovieMainContract.View) :
    MovieMainContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepository()
    private lateinit var movieAdapter: MovieAdapter

    fun getAdapter(context: Context): MovieAdapter {
        movieAdapter = MovieAdapter(context, movieChoiceContractView, movieRepository.getAll())
        return movieAdapter
    }
}
