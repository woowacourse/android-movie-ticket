package woowacourse.movie.movieList

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import movie.Screening
import woowacourse.movie.movieList.movieListItem.ScreeningItem

class MovieListAdapter(
    private val screeningList: List<Screening>,
) : BaseAdapter() {
    private val viewMap = mapOf(
        Screening::class to ScreeningItem,
    )
    override fun getCount(): Int {
        return screeningList.size
    }

    override fun getItem(position: Int): Screening {
        return screeningList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
        viewMap[screeningList[position]::class]?.getView(screeningList[position], convertView, parent)
            ?: throw IllegalArgumentException("Not supported view type")
}
