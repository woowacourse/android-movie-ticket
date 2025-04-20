package woowacourse.movie.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.utils.DateFormatter

class MovieListAdapter(
    private val items: List<Movie>,
    private val onReserveClick: (Movie) -> Unit,
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
        val viewHolder: ViewHolder
        val item = items[position]

        if (convertView == null) {
            view =
                LayoutInflater
                    .from(parent?.context)
                    .inflate(R.layout.item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val dateFormatter = DateFormatter()
        val formattedStartDate = dateFormatter.format(item.date.startDate)
        val formattedEndDate = dateFormatter.format(item.date.endDate)

        viewHolder.imageView.setImageResource(item.image)
        viewHolder.titleTextView.text = item.title
        viewHolder.dateTextView.text = view.context.getString(R.string.movieDate, formattedStartDate, formattedEndDate)
        viewHolder.timeTextView.text = view.context.getString(R.string.movieTime, item.time.toString())
        viewHolder.reserveButton.setOnClickListener {
            onReserveClick(item)
        }

        return view
    }
}
