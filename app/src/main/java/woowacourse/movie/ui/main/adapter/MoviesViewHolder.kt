package woowacourse.movie.ui.main.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningDate
import java.time.format.DateTimeFormatter

sealed class MoviesViewHolder(itemView: View) : ViewHolder(itemView) {
    class MovieViewHolder(private val view: View, private val onClick: (Movie) -> Unit) :
        MoviesViewHolder(view) {
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

    class AdvertisementViewHolder(private val view: View) :
        MoviesViewHolder(view) {
        private val advertisementImageView = view.findViewById<ImageView>(R.id.iv_advertisement)

        fun bind(imageResource: Int) {
            advertisementImageView.setImageResource(imageResource)
        }
    }
}
