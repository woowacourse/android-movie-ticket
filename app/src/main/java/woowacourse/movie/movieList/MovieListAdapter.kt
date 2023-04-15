package woowacourse.movie.movieList

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import entity.MovieListType
import entity.Screening
import woowacourse.movie.movieList.movieListItem.MovieListItem
import woowacourse.movie.movieList.movieListItem.ScreeningItem

class MovieListAdapter(
    private val screeningList: List<MovieListType>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return screeningList.size
    }

    override fun getItem(position: Int): MovieListType {
        return screeningList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
        getItemView(screeningList[position]).getView(screeningList[position], convertView, parent)

    private fun getItemView(movieListType: MovieListType): MovieListItem = when (movieListType) {
        is Screening -> ScreeningItem
    }
}
