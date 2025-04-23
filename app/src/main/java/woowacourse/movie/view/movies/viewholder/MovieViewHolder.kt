package woowacourse.movie.view.movies.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.movies.adapter.AbstractListViewAdapter.ViewHolder

class MovieViewHolder(
    itemView: View,
    onClickBooking: (Int) -> Unit,
) : ViewHolder(itemView) {
    private var mPosition: Int = NO_POSITION

    private val moviePoster = itemView.findViewById<ImageView>(R.id.img_poster)
    private val movieTitle = itemView.findViewById<TextView>(R.id.tv_title)
    private val movieReleaseDate = itemView.findViewById<TextView>(R.id.tv_release_date)
    private val movieRunningTime = itemView.findViewById<TextView>(R.id.tv_running_time)
    private val bookingBtn = itemView.findViewById<Button>(R.id.btn_booking)

    init {
        bookingBtn.setOnClickListener { onClickBooking(mPosition) }
    }

    fun bind(
        item: Movie,
        position: Int,
    ) {
        mPosition = position
        moviePoster.setImageResource(item.poster.toInt())
        movieTitle.text = item.title
        movieReleaseDate.text =
            itemView.context.getString(R.string.text_date_period).format(
                StringFormatter.dotDateFormat(item.releaseDate.startDate),
                StringFormatter.dotDateFormat(item.releaseDate.endDate),
            )
        movieRunningTime.text =
            movieRunningTime.context.getString(R.string.text_running_time_ㅡminute_unit)
                .format(item.runningTime)
    }

    companion object {
        private const val NO_POSITION: Int = -1
    }
}
