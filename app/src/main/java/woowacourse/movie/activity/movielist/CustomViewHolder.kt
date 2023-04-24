package woowacourse.movie.activity.movielist

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieModel
import java.time.format.DateTimeFormatter

sealed class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    class MovieItemViewHolder(view: View) : CustomViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.img_movie)
        private val title: TextView = view.findViewById(R.id.text_title)
        private val playingDate: TextView = view.findViewById(R.id.text_playing_date)
        private val runningTime: TextView = view.findViewById(R.id.text_running_time)
        private val reserveButton: Button = view.findViewById(R.id.btn_reserve)

        fun set(movie: MovieModel, clickListener: View.OnClickListener) {
            val context = title.context
            image.setImageResource(movie.image)
            title.text = movie.title
            playingDate.text = context.getString(
                R.string.playing_date_range,
                DateTimeFormatter.ofPattern(context.getString(R.string.date_format))
                    .format(movie.startDate),
                DateTimeFormatter.ofPattern(context.getString(R.string.date_format))
                    .format(movie.endDate),
            )
            runningTime.text = context.getString(R.string.running_time, movie.runningTime)
            reserveButton.setOnClickListener(clickListener)
        }
    }

    class AdItemViewHolder(view: View) : CustomViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.img_ad)
        fun set(@DrawableRes resId: Int) {
            image.setImageResource(resId)
        }
    }
}
