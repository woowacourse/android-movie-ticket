package woowacourse.movie.view.movieSelection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.view.model.MovieUiModel

class MovieAdapter(
    private val movies: List<MovieUiModel>,
    private val onReservationClick: (MovieUiModel) -> Unit,
) : BaseAdapter() {
    private val viewHolderCache = mutableMapOf<View, MovieViewHolder>()

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): MovieUiModel = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_movie, parent, false)
            viewHolder = MovieViewHolder(view)
            viewHolderCache[view] = viewHolder
        } else {
            view = convertView
            viewHolder = viewHolderCache[view] ?: MovieViewHolder(view).also {
                viewHolderCache[view] = it
            }
        }

        viewHolder.bind(getItem(position), onReservationClick)
        return view
    }
}
