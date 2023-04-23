package woowacourse.movie.ui.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.ui.DateTimeFormatters

class MovieViewHolder(private val view: View) : ViewHolder(view) {
    private val ivPoster: ImageView = view.findViewById(R.id.iv_poster)
    private val tvMovieName: TextView = view.findViewById(R.id.tv_movie_name)
    private val tvScreeningDay: TextView = view.findViewById(R.id.tv_screening_day)
    private val tvRunningTime: TextView = view.findViewById(R.id.tv_running_time)
    private val btnBooking: Button = view.findViewById(R.id.btn_booking)

    fun bind(item: MovieDataState, onButtonClickListener: (item: MovieDataState) -> Unit) {
        ivPoster.setImageResource(item.posterImage)
        tvMovieName.text = item.title
        tvScreeningDay.text = view.context.getString(R.string.screening_date_format)
            .format(
                item.screeningDay.start.format(DateTimeFormatters.hyphenDateFormatter),
                item.screeningDay.end.format(DateTimeFormatters.hyphenDateFormatter)
            )
        tvRunningTime.text = view.context.getString(R.string.running_time_format)
            .format(item.runningTime)
        btnBooking.setOnClickListener {
            onButtonClickListener(item)
        }
    }
}
