package woowacourse.movie.view.movie.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.StringFormatter

class MovieViewHolder(
    private val itemView: View,
    private val onClickBooking: (Movie) -> Unit,
) {
    private val moviePoster = itemView.findViewById<ImageView>(R.id.img_poster)
    private val movieTitle = itemView.findViewById<TextView>(R.id.tv_title)
    private val movieReleaseDate = itemView.findViewById<TextView>(R.id.tv_release_date)
    private val movieRunningTime = itemView.findViewById<TextView>(R.id.tv_running_time)
    private val bookingBtn = itemView.findViewById<Button>(R.id.btn_booking)

    fun bind(item: Movie) {
        moviePoster.setImageResource(item.poster.toInt())
        movieTitle.text = item.title
        movieReleaseDate.text =
            itemView.context.getString(R.string.text_date_period).format(
                StringFormatter.dotDateFormat(item.releaseDate.startDate),
                StringFormatter.dotDateFormat(item.releaseDate.endDate),
            )
        movieRunningTime.text = item.runningTime
        bookingBtn.setOnClickListener { onClickBooking(item) }
    }
}
