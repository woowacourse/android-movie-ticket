package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.domain.Movie
import java.time.format.DateTimeFormatter

class MovieAdapter(
    private val items: List<Movie>,
    private val onButtonClick: (Movie) -> Unit,
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

        val movie = getItem(position)
        viewHolder.poster.setImageResource(movie.poster)
        viewHolder.title.text = movie.title
        val startDate = movie.startDate.format(DATE_FORMAT)
        val endDate = movie.endDate.format(DATE_FORMAT)
        viewHolder.screeningDate.text = SCREENING_DATE_RANGE.format(startDate, endDate)
        viewHolder.runningTime.text = RUNNING_TIME.format(movie.runningTime)

        viewHolder.reserveButton.setOnClickListener { onButtonClick(movie) }
        return view
    }

    companion object {
        const val EXTRA_MOVIE = "movie"
        private const val SCREENING_DATE_RANGE = "%s ~ %S"
        private const val RUNNING_TIME = "%d분"
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
