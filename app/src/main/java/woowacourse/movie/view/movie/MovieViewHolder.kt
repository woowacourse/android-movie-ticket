package woowacourse.movie.view.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.utils.StringFormatter.periodFormat

class MovieViewHolder(
    val convertView: View,
    val onClickBooking: (Movie) -> Unit,
) {
    val moviePoster: ImageView = convertView.findViewById<ImageView>(R.id.img_poster)
    val movieTitle: TextView = convertView.findViewById<TextView>(R.id.tv_title)
    val movieReleaseDate: TextView = convertView.findViewById<TextView>(R.id.tv_release_date)
    val movieRunningTime: TextView = convertView.findViewById<TextView>(R.id.tv_running_time)
    val bookingBtn: Button = convertView.findViewById<Button>(R.id.btn_booking)

    fun bind(item: Movie) {
        with(item) {
            moviePoster.setImageResource(posterId)
            movieTitle.text = title
            movieReleaseDate.text =
                periodFormat(releaseDate.startDate, releaseDate.endDate)
            movieRunningTime.text =
                convertView.context.getString(R.string.text_minute).format(runningTime)
            bookingBtn.setOnClickListener {
                onClickBooking(this)
            }
        }
    }
}
