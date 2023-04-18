package woowacourse.movie.ui.movielistactivity

import android.content.Intent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ui.DateTimeFormatters.hyphenDateFormatter
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.moviebookingactivity.MovieBookingActivity
import woowacourse.movie.util.getString
import woowacourse.movie.util.setOnSingleClickListener

class MovieViewHolder(
    val ivPoster: ImageView,
    val tvMovieName: TextView,
    val tvScreeningDay: TextView,
    val tvRunningTime: TextView,
    val btnBooking: Button
) {
    fun setData(itemData: MovieUIModel) {
        ivPoster.setImageResource(itemData.posterImage)
        tvMovieName.text = itemData.title
        tvScreeningDay.text = tvScreeningDay.getString(R.string.screening_date_format)
            .format(
                itemData.screeningDay.start.format(hyphenDateFormatter),
                itemData.screeningDay.end.format(hyphenDateFormatter)
            )
        tvRunningTime.text = tvRunningTime.getString(R.string.running_time_format)
            .format(itemData.runningTime)

        btnBooking.setOnSingleClickListener {
            val intent = Intent(it.context, MovieBookingActivity::class.java)
                .putExtra(MovieBookingActivity.MOVIE_DATA, itemData)
            it.context.startActivity(intent)
        }
    }
}
