package woowacourse.movie.view.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.StringFormatter.dotDateFormat

class MovieAdapter(
    val onClickBooking: (Int) -> Unit,
    private val items: List<Movie>,
    private val datePeriod: String,
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
        val viewHolder: MovieViewHolder

        val item: Movie = getItem(position)

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
            viewHolder = MovieViewHolder(position, view, onClickBooking)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as MovieViewHolder
        }

        bind(position, viewHolder, item)
        return view
    }

    private fun bind(
        position: Int,
        viewHolder: MovieViewHolder,
        item: Movie,
    ) {
        viewHolder.position = position
        viewHolder.moviePoster.setImageResource(item.poster)
        viewHolder.movieTitle.text = item.title
        viewHolder.movieReleaseDate.text =
            datePeriod.format(
                dotDateFormat(item.releaseDate.startDate),
                dotDateFormat(item.releaseDate.endDate),
            )
        viewHolder.movieRunningTime.text = item.runningTime
    }
}
