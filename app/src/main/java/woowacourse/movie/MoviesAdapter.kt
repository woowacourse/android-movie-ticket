package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningDate
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
        val (view, viewHolder) = reuseOrCreateView(convertView, parent)
        val item: Movie = movies[position]
        viewHolder.bind(item)
        initReserveButton(view, item)
        return view
    }

    private fun reuseOrCreateView(
        convertView: View?,
        parent: ViewGroup?,
    ): Pair<View, ViewHolder> {
        return if (convertView == null) {
            val view =
                LayoutInflater.from(parent?.context)
                    .inflate(R.layout.movie_item, parent, false)
            val viewHolder = ViewHolder(view)
            view.tag = viewHolder
            Pair(view, viewHolder)
        } else {
            val viewHolder = convertView.tag as ViewHolder
            Pair(convertView, viewHolder)
        }
    }

    class ViewHolder(private val view: View) {
        private val posterView: ImageView = view.findViewById(R.id.iv_poster)
        private val titleView: TextView = view.findViewById(R.id.tv_title)
        private val screeningDateView: TextView = view.findViewById(R.id.tv_screening_date)
        private val runningTimeView: TextView = view.findViewById(R.id.tv_running_time)

        fun bind(movie: Movie) {
            with(movie) {
                val formattedScreeningDate = formatScreeningDate(view, screeningDate)
                posterView.setImageResource(imageUrl)
                titleView.text = title
                screeningDateView.text = formattedScreeningDate
                runningTimeView.text =
                    view.context.getString(R.string.formatted_minute, runningTime.time)
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

    private fun initReserveButton(
        itemView: View,
        item: Movie,
    ) {
        val reserveBtn = itemView.findViewById<Button>(R.id.btn_reserve)
        reserveBtn.setOnClickListener {
            onClick(item)
        }
    }
}
