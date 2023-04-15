package woowacourse.movie.movieList

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import movie.Cinema
import movie.Screening

class MovieListAdapter(
    private val Cinema: Cinema,
) : BaseAdapter() {
    override fun getCount(): Int {
        return Cinema.size
    }

    override fun getItem(position: Int): Screening {
        return Cinema[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return MovieListItemView.getView(Cinema[position], convertView, parent)
    }
}
