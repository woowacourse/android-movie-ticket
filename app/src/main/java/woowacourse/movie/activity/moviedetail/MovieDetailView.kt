package woowacourse.movie.activity.moviedetail

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailView(private val viewGroup: ViewGroup) {
    fun set(movie: MovieModel) {
        setImageView(movie.image)
        setTitle(movie.title)
        setPlayingDate(movie.startDate, movie.endDate)
        setRunningTime(movie.runningTime)
        setDescription(movie.description)
    }

    private fun setDescription(description: String) {
        viewGroup.findViewById<TextView>(R.id.text_description).text = description
    }

    private fun setRunningTime(runningTime: Int) {
        viewGroup.findViewById<TextView>(R.id.text_running_time).text = viewGroup.context.getString(R.string.running_time, runningTime)
    }

    private fun setPlayingDate(startDate: LocalDate, endDate: LocalDate) {
        viewGroup.findViewById<TextView>(R.id.text_playing_date).text = viewGroup.context.getString(
            R.string.playing_date_range,
            DateTimeFormatter.ofPattern(viewGroup.context.getString(R.string.date_format)).format(startDate),
            DateTimeFormatter.ofPattern(viewGroup.context.getString(R.string.date_format)).format(endDate)
        )
    }

    private fun setTitle(title: String) {
        viewGroup.findViewById<TextView>(R.id.text_title).text = title
    }

    private fun setImageView(image: Int) {
        viewGroup.findViewById<ImageView>(R.id.img_movie).setImageResource(image)
    }
}
