package woowacourse.movie.view

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.dto.MovieDto
import java.time.format.DateTimeFormatter

class MovieView(
    private val context: Context,
    private val movieDto: MovieDto,
    private val poster: ImageView? = null,
    private val title: TextView? = null,
    private val date: TextView? = null,
    private val runningTime: TextView? = null,
    private val description: TextView? = null
) {
    fun render() {
        poster?.setImageResource(movieDto.picture)
        title?.text = movieDto.title

        val dateFormat = DateTimeFormatter.ofPattern(context.getString(R.string.movie_date_format))
        date?.text = context.getString(R.string.movie_date).format(
            dateFormat.format(movieDto.startDate), dateFormat.format(movieDto.endDate)
        )
        runningTime?.text = context.getString(R.string.movie_running_time).format(movieDto.runningTime)
        description?.text = movieDto.description
    }
}
