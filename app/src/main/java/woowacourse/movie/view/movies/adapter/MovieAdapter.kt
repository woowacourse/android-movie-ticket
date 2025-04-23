package woowacourse.movie.view.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.movies.OnMovieEventListener

class MovieAdapter(
    private val items: List<Movie>,
    private val eventListener: OnMovieEventListener,
) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            view =
                LayoutInflater
                    .from(parent?.context)
                    .inflate(R.layout.movie_item, parent, false)
            viewHolder = MovieViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as MovieViewHolder
        }

        viewHolder.bind(items[position], eventListener)

        return view
    }
}
