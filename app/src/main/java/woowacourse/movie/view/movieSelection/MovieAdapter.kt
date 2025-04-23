package woowacourse.movie.view.movieSelection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.model.MovieUiModel
import java.time.format.DateTimeFormatter

class MovieAdapter(
    private val movies: List<MovieUiModel>,
    private val onReservationClick: (MovieUiModel) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): MovieUiModel = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        container: ViewGroup,
    ): View {
        val context: Context = container.context
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, container, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val movie = getItem(position)
        val formatter = DateTimeFormatter.ofPattern(context.getString(R.string.date_format))
        val startDate = movie.startDate.format(formatter)
        val endDate = movie.endDate.format(formatter)

        viewHolder.poster.setImageResource(movie.poster)
        viewHolder.title.text = movie.title
        viewHolder.screeningDate.text = context.getString(R.string.screening_dates_format, startDate, endDate)
        viewHolder.runningTime.text = context.getString(R.string.running_type_format, movie.runningTime)
        viewHolder.reserveButton.setOnClickListener { onReservationClick(movie) }

        return view
    }

    companion object {
        const val KEY_MOVIE = "movie"
    }
}

private class ViewHolder(view: View) {
    val poster: ImageView = view.findViewById(R.id.poster)
    val title: TextView = view.findViewById(R.id.movie_title)
    val screeningDate: TextView = view.findViewById(R.id.screening_date)
    val runningTime: TextView = view.findViewById(R.id.running_time)
    val reserveButton: Button = view.findViewById(R.id.reserve_button)
}
