package woowacourse.movie.movieList.movieListItem

import android.view.View
import android.view.ViewGroup
import entity.MovieListType

sealed interface MovieListItem {
    fun getView(movieListType: MovieListType, convertView: View?, parent: ViewGroup?): View
}
