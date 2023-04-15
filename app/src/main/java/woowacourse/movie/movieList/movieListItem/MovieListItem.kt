package woowacourse.movie.movieList.movieListItem

import android.view.View
import android.view.ViewGroup
import entity.MovieListDto

sealed interface MovieListItem {
    fun getView(movieListDto: MovieListDto, convertView: View?, parent: ViewGroup?): View
}
