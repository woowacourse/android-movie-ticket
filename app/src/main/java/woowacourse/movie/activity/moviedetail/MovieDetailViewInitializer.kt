package woowacourse.movie.activity.moviedetail

import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.movie.MovieDTO
import java.time.format.DateTimeFormatter

class MovieDetailViewInitializer(private val movieDTO: MovieDTO) {

    fun initDescription(textView: TextView) {
        textView.text = movieDTO.description
    }

    fun initRunningTime(textView: TextView) {
        textView.text = textView.context.getString(R.string.running_time, movieDTO.runningTime)
    }

    fun initPlayingDate(textView: TextView) {
        textView.text = textView.context.getString(
            R.string.playing_date_range,
            DateTimeFormatter.ofPattern(textView.context.getString(R.string.date_format)).format(movieDTO.startDate),
            DateTimeFormatter.ofPattern(textView.context.getString(R.string.date_format)).format(movieDTO.endDate)
        )
    }

    fun initTitle(textView: TextView) {
        textView.text = movieDTO.title
    }

    fun initImageView(imageView: ImageView) {
        imageView.setImageResource(movieDTO.image)
    }
}
