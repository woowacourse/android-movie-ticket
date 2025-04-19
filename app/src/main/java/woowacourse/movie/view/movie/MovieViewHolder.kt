package woowacourse.movie.view.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.StringFormatter.periodFormat

class MovieViewHolder(
    var position: Int,
    convertView: View,
    onClickBooking: (Int) -> Unit,
) {
    val moviePoster: ImageView = convertView.findViewById<ImageView>(R.id.img_poster)
    val movieTitle: TextView = convertView.findViewById<TextView>(R.id.tv_title)
    val movieReleaseDate: TextView = convertView.findViewById<TextView>(R.id.tv_release_date)
    val movieRunningTime: TextView = convertView.findViewById<TextView>(R.id.tv_running_time)
    val bookingBtn: Button = convertView.findViewById<Button>(R.id.btn_booking)

    init {
        bookingBtn.setOnClickListener {
            onClickBooking(position)
        }
    }

    fun bind(
        position: Int,
        viewHolder: MovieViewHolder,
        item: Movie,
    ) {
        viewHolder.position = position
        viewHolder.moviePoster.setImageResource(item.poster)
        viewHolder.movieTitle.text = item.title
        viewHolder.movieReleaseDate.text =
            periodFormat(item.releaseDate.startDate, item.releaseDate.endDate)
        viewHolder.movieRunningTime.text = item.runningTime
    }
}
