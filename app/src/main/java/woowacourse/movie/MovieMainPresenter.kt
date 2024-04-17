package woowacourse.movie

import android.content.Context

class MovieMainPresenter(private val movieChoiceContractView: MovieMainContract.View) : MovieMainContract.Presenter {
    private val movieRepository: MovieRepository = MovieRepository()
    private lateinit var movieAdapter: MovieAdapter

    fun getAdapter(context: Context): MovieAdapter {
        movieAdapter = MovieAdapter(context, movieChoiceContractView, movieRepository.movies)
        return movieAdapter
    }
}
