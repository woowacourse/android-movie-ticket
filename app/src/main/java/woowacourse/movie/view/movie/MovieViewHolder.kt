package woowacourse.movie.view.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.utils.StringFormatter.periodFormat

class MovieViewHolder(
    itemView: View,
    val onClickBooking: (Movie) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    private var currentMovieItem: Movie? = null

    val moviePoster: ImageView = itemView.findViewById<ImageView>(R.id.img_advertisement)
    val movieTitle: TextView = itemView.findViewById<TextView>(R.id.tv_title)
    val movieReleaseDate: TextView = itemView.findViewById<TextView>(R.id.tv_release_date)
    val movieRunningTime: TextView = itemView.findViewById<TextView>(R.id.tv_running_time)
    val bookingBtn: Button = itemView.findViewById<Button>(R.id.btn_booking)

    init {
        bookingBtn.setOnClickListener {
            currentMovieItem?.let {
                onClickBooking(it)
            }
        }
    }

    fun bind(item: Movie) {
        with(item) {
            currentMovieItem = this
            moviePoster.setImageResource(posterId)
            movieTitle.text = title
            movieReleaseDate.text =
                periodFormat(releaseDate.startDate, releaseDate.endDate)
            movieRunningTime.text =
                itemView.context.getString(R.string.text_minute).format(runningTime)
            bookingBtn.setOnClickListener {
                onClickBooking(this)
            }
        }
    }
}
