package woowacourse.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningDate
import java.time.format.DateTimeFormatter

class MoviesViewHolder(
    private val itemView: View,
    private var item: Movie,
    val onClick: (Movie) -> Unit,
) {
    private val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    private val title: TextView = itemView.findViewById(R.id.tv_title)
    private val screeningDate: TextView = itemView.findViewById(R.id.tv_screening_date)
    private val runningTime: TextView = itemView.findViewById(R.id.tv_running_time)
    private val reserveBtn: Button = itemView.findViewById(R.id.btn_reserve)

    init {
        reserveBtn.setOnClickListener {
            onClick(item)
        }
    }

    fun bind(item: Movie) {
        this.item = item
        poster.setImageResource(item.imageUrl)
        title.text = item.title
        screeningDate.text =
            formatting(
                item.screeningDate,
                itemView.context.getString(R.string.formatted_screening_period),
            )
        runningTime.text =
            itemView.context.getString(R.string.formatted_minute)
                .format(item.runningTime.time)
    }

    private fun formatting(
        screeningDate: ScreeningDate,
        periodTemplate: String,
    ): String {
        val start = screeningDate.startDate.format(formatter)
        val end = screeningDate.endDate.format(formatter)
        return periodTemplate.format(start, end)
    }

    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
