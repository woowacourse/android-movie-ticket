package woowacourse.movie.activity.moviedetail

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailView(private val viewGroup: ViewGroup) {
    private val descriptionView = viewGroup.findViewById<TextView>(R.id.text_description)
    private val runningTimeView = viewGroup.findViewById<TextView>(R.id.text_running_time)
    private val playingDateView = viewGroup.findViewById<TextView>(R.id.text_playing_date)
    private val titleView = viewGroup.findViewById<TextView>(R.id.text_title)
    private val imageView = viewGroup.findViewById<ImageView>(R.id.img_movie)

    fun set(movie: MovieModel) {
        setImageView(movie.image)
        setTitle(movie.title)
        setPlayingDate(movie.startDate, movie.endDate)
        setRunningTime(movie.runningTime)
        setDescription(movie.description)
    }

    private fun setDescription(description: String) {
        descriptionView.text = description
    }

    private fun setRunningTime(runningTime: Int) {
        runningTimeView.text = viewGroup.context.getString(R.string.running_time, runningTime)
    }

    private fun setPlayingDate(startDate: LocalDate, endDate: LocalDate) {
        playingDateView.text = viewGroup.context.getString(
            R.string.playing_date_range,
            DateTimeFormatter.ofPattern(viewGroup.context.getString(R.string.date_format))
                .format(startDate),
            DateTimeFormatter.ofPattern(viewGroup.context.getString(R.string.date_format))
                .format(endDate),
        )
    }

    private fun setTitle(title: String) {
        titleView.text = title
    }

    private fun setImageView(image: Int) {
        imageView.setImageResource(image)
    }
}
