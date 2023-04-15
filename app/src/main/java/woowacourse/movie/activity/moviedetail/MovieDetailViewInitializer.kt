package woowacourse.movie.activity.moviedetail

import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import java.time.format.DateTimeFormatter

class MovieDetailViewInitializer(private val movie: Movie) {

    fun initDescription(textView: TextView) {
        textView.text = movie.description
    }

    fun initRunningTime(textView: TextView) {
        textView.text = textView.context.getString(R.string.running_time, movie.runningTime)
    }

    fun initPlayingDate(textView: TextView) {
        textView.text = textView.context.getString(
            R.string.playing_time,
            DateTimeFormatter.ofPattern(textView.context.getString(R.string.date_format)).format(movie.playingTimes.startDate),
            DateTimeFormatter.ofPattern(textView.context.getString(R.string.date_format)).format(movie.playingTimes.endDate)
        )
    }

    fun initTitle(textView: TextView) {
        textView.text = movie.title
    }

    fun initImageView(imageView: ImageView) {
        imageView.setImageResource(movie.image)
    }
}
