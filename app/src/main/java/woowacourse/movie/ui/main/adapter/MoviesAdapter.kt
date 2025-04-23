package woowacourse.movie.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningDate
import java.time.format.DateTimeFormatter

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
        return reuseOrCreateView(convertView, parent, item)
    }

    private fun reuseOrCreateView(
        convertView: View?,
        parent: ViewGroup?,
        item: Movie,
    ): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view =
                LayoutInflater.from(parent?.context)
                    .inflate(R.layout.movie_item, parent, false)
            viewHolder = ViewHolder(view, onClick)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.bind(item)
        return view
    }

    private class ViewHolder(private val view: View, private val onClick: (Movie) -> Unit) {
        private val posterView: ImageView = view.findViewById(R.id.iv_poster)
        private val titleView: TextView = view.findViewById(R.id.tv_title)
        private val screeningDateView: TextView = view.findViewById(R.id.tv_screening_date)
        private val runningTimeView: TextView = view.findViewById(R.id.tv_running_time)
        private val reserveBtn = view.findViewById<Button>(R.id.btn_reserve)

        fun bind(movie: Movie) {
            with(movie) {
                val formattedScreeningDate = formatScreeningDate(view, screeningDate)
                posterView.setImageResource(imageUrl)
                titleView.text = title
                screeningDateView.text = formattedScreeningDate
                runningTimeView.text =
                    view.context.getString(R.string.formatted_minute, runningTime.time)
                reserveBtn.setOnClickListener {
                    onClick(movie)
                }
            }
        }

        private fun formatScreeningDate(
            view: View,
            screeningDate: ScreeningDate,
        ): String {
            val formatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern(view.context.getString(R.string.date_format))
            val start = screeningDate.startDate.format(formatter)
            val end = screeningDate.endDate.format(formatter)
            return view.context.getString(R.string.formatted_screening_date, start, end)
        }
    }
}
