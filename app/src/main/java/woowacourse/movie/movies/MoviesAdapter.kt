package woowacourse.movie.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R

class MoviesAdapter(
    private val movies: List<MovieUiModel>,
    private val onBookingClick: (MovieUiModel) -> Unit,
) : BaseAdapter() {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val viewHolder: MovieItemViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
            viewHolder = MovieItemViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as MovieItemViewHolder
        }

        val movie = getItem(position)

        viewHolder.bind(movie, onBookingClick)

        return view
    }

    override fun getItem(position: Int): MovieUiModel = movies[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = movies.size
}
