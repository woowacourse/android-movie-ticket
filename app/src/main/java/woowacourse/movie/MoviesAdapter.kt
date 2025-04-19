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
        val validateParent = requireNotNull(parent) { "Parent ViewGroup must not be null" }
        val itemView =
            convertView ?: LayoutInflater.from(validateParent.context)
                .inflate(R.layout.movie_item, parent, false)

        val item: Movie = movies[position]
        initItemView(validateParent, itemView, item)
        initReserveButton(itemView, item)

        return itemView
    }

    private fun initItemView(
        parent: ViewGroup,
        itemView: View,
        item: Movie,
    ) {
        val poster = itemView.findViewById<ImageView>(R.id.iv_poster)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val screeningDate = itemView.findViewById<TextView>(R.id.tv_screening_date)
        val runningTime = itemView.findViewById<TextView>(R.id.tv_running_time)

        itemView.parent
        val formattedScreeningDate = formatScreeningDate(parent, item.screeningDate)

        poster.setImageResource(item.imageUrl)
        title.text = item.title
        screeningDate.text = formattedScreeningDate
        runningTime.text =
            parent.context.getString(R.string.formatted_minute, item.runningTime.time)
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

    private fun formatScreeningDate(
        parent: ViewGroup,
        screeningDate: ScreeningDate,
    ): String {
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern(parent.context.getString(R.string.date_format))
        val start = screeningDate.startDate.format(formatter)
        val end = screeningDate.endDate.format(formatter)
        return parent.context.getString(R.string.formatted_screening_date, start, end)
    }
}
