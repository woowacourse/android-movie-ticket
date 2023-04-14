package woowacourse.movie.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.data.Movie

class MovieListAdapter(
    private val movies: List<Movie>,
    private val onBookClickListener: OnBookClickListener,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val viewHolder: MovieListViewHolder
        var view: View? = convertView
        if (view == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_movie, parent, false)
            viewHolder = MovieListViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as MovieListViewHolder
        }
        viewHolder.bind(movies[position], onBookClickListener)
        return view
    }
}
