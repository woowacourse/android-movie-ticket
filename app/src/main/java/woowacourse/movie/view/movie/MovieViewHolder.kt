package woowacourse.movie.view.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

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
}
