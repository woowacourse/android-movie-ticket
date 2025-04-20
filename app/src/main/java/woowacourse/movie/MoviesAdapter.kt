package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.domain.Movie

class MoviesAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.count()

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val item: Movie = movies[position]
        val viewHolder: MoviesViewHolder
        val itemView: View

        if (convertView == null) {
            itemView =
                LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
            viewHolder = MoviesViewHolder(itemView, item, onClick)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = convertView.tag as MoviesViewHolder
        }
        viewHolder.bind(item)

        return itemView
    }
}
