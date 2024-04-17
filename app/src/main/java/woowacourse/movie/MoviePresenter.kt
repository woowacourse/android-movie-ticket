package woowacourse.movie

import android.content.Context

class MoviePresenter(private val viewInterface: ViewInterface) {
    private val sampleMovies: MovieRepository = MovieRepository()
    private lateinit var movieAdapter: MovieAdapter

    fun getAdapter(context: Context): MovieAdapter {
        movieAdapter = MovieAdapter(context, viewInterface, sampleMovies.movies)
        return movieAdapter
    }
}
