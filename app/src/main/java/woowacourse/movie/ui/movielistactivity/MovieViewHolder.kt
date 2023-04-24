package woowacourse.movie.ui.movielistactivity

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.ui.DateTimeFormatters.hyphenDateFormatter
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.util.getString
import woowacourse.movie.util.setOnSingleClickListener

class MovieViewHolder(
    item: View,
    getPositionData: (Int) -> MovieUIModel,
    clickListener: (MovieUIModel) -> Unit
) : ViewHolder(item) {

    private val ivPoster: ImageView = item.findViewById(R.id.iv_poster)
    private val tvMovieName: TextView = item.findViewById(R.id.tv_movie_name)
    private val tvScreeningDay: TextView = item.findViewById(R.id.tv_screening_day)
    private val tvRunningTime: TextView = item.findViewById(R.id.tv_running_time)
    private val btnBooking: Button = item.findViewById(R.id.btn_booking)

    init {
        initBtnBookingClickListener(getPositionData, clickListener)
    }

    fun setData(itemData: MovieUIModel) {
        ivPoster.setImageResource(itemData.posterImage)
        tvMovieName.text = itemData.title
        tvScreeningDay.text = tvScreeningDay.getString(R.string.screening_date_format)
            .format(
                itemData.screeningStartDay.format(hyphenDateFormatter),
                itemData.screeningEndDay.format(hyphenDateFormatter)
            )
        tvRunningTime.text = tvRunningTime.getString(R.string.running_time_format)
            .format(itemData.runningTime)
    }

    private fun initBtnBookingClickListener(
        getPositionData: (Int) -> MovieUIModel,
        clickListener: (MovieUIModel) -> Unit
    ) {
        btnBooking.setOnSingleClickListener {
            clickListener(getPositionData(adapterPosition))
        }
    }
}
