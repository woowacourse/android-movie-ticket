package woowacourse.movie.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.Movie

class MovieAdapter(
    private val items: List<Movie>,
    private val onClickButton: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Movie = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        container: ViewGroup?,
    ): View {
        val view: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            view =
                LayoutInflater
                    .from(container?.context)
                    .inflate(R.layout.item_movie, container, false)
            viewHolder = MovieViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as MovieViewHolder
        }
        viewHolder.bind(getItem(position), onClickButton)
        return view
    }
}
