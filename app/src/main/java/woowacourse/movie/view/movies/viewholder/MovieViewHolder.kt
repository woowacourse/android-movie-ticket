package woowacourse.movie.view.movies.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.ext.toDrawableResourceId
import woowacourse.movie.view.movies.model.UiModel.MovieUiModel

class MovieViewHolder(
    itemView: View,
    onClickBooking: (Int) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    private var mId: Int = NO_ID
    private val context = itemView.context

    private val moviePoster = itemView.findViewById<ImageView>(R.id.img_poster)
    private val movieTitle = itemView.findViewById<TextView>(R.id.tv_title)
    private val movieReleaseDate = itemView.findViewById<TextView>(R.id.tv_release_date)
    private val movieRunningTime = itemView.findViewById<TextView>(R.id.tv_running_time)
    private val bookingBtn = itemView.findViewById<Button>(R.id.btn_booking)

    init {
        bookingBtn.setOnClickListener { onClickBooking(mId) }
    }

    fun bind(item: MovieUiModel) {
        with(item) {
            mId = id
            moviePoster.setImageResource(imgName.toDrawableResourceId(context))

            movieTitle.text = title
            movieReleaseDate.text =
                context
                    .getString(R.string.text_date_period)
                    .format(
                        releaseStartDate,
                        releaseEndDate,
                    )

            movieRunningTime.text =
                context
                    .getString(R.string.text_running_time_ㅡminute_unit)
                    .format(runningTime)
        }
    }

    companion object {
        private const val NO_ID: Int = -1
    }
}
