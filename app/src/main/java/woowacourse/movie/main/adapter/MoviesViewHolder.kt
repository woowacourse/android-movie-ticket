package woowacourse.movie.main.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningDate
import java.time.format.DateTimeFormatter

class MoviesViewHolder(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    private val poster: ImageView = itemView.findViewById(R.id.iv_poster)
    private val title: TextView = itemView.findViewById(R.id.tv_title)
    private val screeningDate: TextView = itemView.findViewById(R.id.tv_screening_date)
    private val runningTime: TextView = itemView.findViewById(R.id.tv_running_time)
    val reserveBtn: Button = itemView.findViewById(R.id.btn_reserve)

    fun binding(movie: Movie) {
        poster.setImageResource(movie.imageUrl)
        title.text = movie.title
        screeningDate.text =
            formatting(
                movie.screeningDate,
                itemView.context.getString(R.string.text_screening_date),
            )
        runningTime.text =
            itemView.context.getString(R.string.text_running_time)
                .format(movie.runningTime.time)
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
