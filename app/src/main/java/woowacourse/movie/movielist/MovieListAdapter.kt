package woowacourse.movie.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.Movie

class MovieListAdapter(
    private val items: List<Movie>,
    private val clickListener: ClickListener,
) : BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Movie = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val movieListViewHolder: MovieListViewHolder
        val item = items[position]

        if (convertView == null) {
            view =
                LayoutInflater
                    .from(parent?.context)
                    .inflate(R.layout.item, parent, false)
            movieListViewHolder = MovieListViewHolder(view)
            view.tag = movieListViewHolder
        } else {
            view = convertView
            movieListViewHolder = view.tag as MovieListViewHolder
        }

        movieListViewHolder.setItem(item)
        movieListViewHolder.reserveButton.setOnClickListener {
            clickListener.onReserveClick(item)
        }

        return view
    }
}
