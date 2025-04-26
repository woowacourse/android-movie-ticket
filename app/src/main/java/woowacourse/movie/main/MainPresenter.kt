package woowacourse.movie.main

import woowacourse.movie.DefaultDatas
import woowacourse.movie.R

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun initMovies() {
        val movies = DefaultDatas.movies.map { Item.MovieItem(it) }
        val result = mutableListOf<Item>()

        movies.forEachIndexed { index, movieItem ->
            result.add(movieItem)
            if ((index + INDEX_OFFSET) % AD_INSERT_INTERVAL == 0) {
                result.add(Item.AdvertisementItem(R.drawable.advertisement))
            }
        }

        view.showMovies(result)
    }

    companion object {
        private const val AD_INSERT_INTERVAL: Int = 3
        private const val INDEX_OFFSET: Int = 1
    }
}
