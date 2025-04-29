package woowacourse.movie.ui.movielist.view

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.PosterIdMapper
import woowacourse.movie.utils.StringFormatter.periodFormat

class MovieViewHolder(
    itemView: View,
    val onClickBooking: (Movie) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    private var currentMovieItem: Movie? = null

    private val moviePoster: ImageView = itemView.findViewById<ImageView>(R.id.img_advertisement)
    private val movieTitle: TextView = itemView.findViewById<TextView>(R.id.tv_title)
    private val movieReleaseDate: TextView = itemView.findViewById<TextView>(R.id.tv_release_date)
    private val movieRunningTime: TextView = itemView.findViewById<TextView>(R.id.tv_running_time)
    private val bookingBtn: Button = itemView.findViewById<Button>(R.id.btn_booking)

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
            moviePoster.setImageResource(PosterIdMapper.posterIdByMovieId(item.id))
            movieTitle.text = title
            movieReleaseDate.text =
                periodFormat(releaseDate.startDate, releaseDate.endDate)
            movieRunningTime.text =
                itemView.context.getString(R.string.text_minute).format(runningTime)
        }
    }
}
