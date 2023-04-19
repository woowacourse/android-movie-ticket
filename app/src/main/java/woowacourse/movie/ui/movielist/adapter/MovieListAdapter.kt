package woowacourse.movie.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.model.MovieUI

class MovieListAdapter(
    private val movies: List<MovieUI>,
    private val onBookClick: (MovieUI) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): MovieUI = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: MovieListViewHolder
        val view: View = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_movie, parent, false)
        if (convertView == null) {
            viewHolder = MovieListViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as MovieListViewHolder
        }
        viewHolder.bind(movies[position], onBookClick)
        return view
    }
}
