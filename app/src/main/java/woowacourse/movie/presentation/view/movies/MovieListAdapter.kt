package woowacourse.movie.presentation.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie

class MovieListAdapter(
    private val movies: List<Movie>,
    private val eventListener: OnMovieEventListener,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
            viewHolder = MovieViewHolder(view, eventListener)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as MovieViewHolder
        }

        viewHolder.bind(getItem(position))
        return view
    }
}
