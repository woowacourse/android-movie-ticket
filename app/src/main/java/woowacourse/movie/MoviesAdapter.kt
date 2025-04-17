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
        var itemView = convertView
        if (itemView == null) {
            itemView =
                LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
        }

        val item: Movie = movies[position]

        initItemView(itemView!!, item)
        initReserveButton(itemView, item)

        return itemView
    }

    private fun initItemView(
        itemView: View,
        item: Movie,
    ) {
        val poster = itemView.findViewById<ImageView>(R.id.iv_poster)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val screeningDate = itemView.findViewById<TextView>(R.id.tv_screening_date)
        val runningTime = itemView.findViewById<TextView>(R.id.tv_running_time)

        val formattedScreeningDate = formatting(item.screeningDate)

        poster.setImageResource(item.imageUrl)
        title.text = item.title
        screeningDate.text = formattedScreeningDate
        runningTime.text = MINUTE.format(item.runningTime.time)
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

    private fun formatting(screeningDate: ScreeningDate): String {
        val start = screeningDate.startDate.format(formatter)
        val end = screeningDate.endDate.format(formatter)
        return SCREENING_DATE.format(start, end)
    }

    companion object {
        private const val MINUTE = "%d분"
        private const val SCREENING_DATE = "%s ~ %s"
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
