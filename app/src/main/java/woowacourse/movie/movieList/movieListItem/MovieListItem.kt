package woowacourse.movie.movieList.movieListItem

import android.view.View
import android.view.ViewGroup
import movie.Screening

interface MovieListItem {
    fun getView(screening: Screening, convertView: View?, parent: ViewGroup?): View
}
