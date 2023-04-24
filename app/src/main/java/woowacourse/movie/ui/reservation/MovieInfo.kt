package woowacourse.movie.ui.reservation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieState
import woowacourse.movie.ui.DateTimeFormatters

class MovieInfo(
    view: View
) {
    private val image: ImageView = view.findViewById(R.id.detail_image)
    private val title: TextView = view.findViewById(R.id.detail_title)
    private val detailDate: TextView = view.findViewById(R.id.detail_date)
    private val detailTime: TextView = view.findViewById(R.id.detail_time)
    private val description: TextView = view.findViewById(R.id.description)

    fun setMovieState(movie: MovieState) {
        image.setImageResource(movie.imgId)
        title.text = movie.title
        detailDate.text =
            DateTimeFormatters.convertToDateTildeDate(
                detailDate.context,
                movie.startDate,
                movie.endDate
            )
        detailTime.text = detailTime.context.getString(R.string.running_time, movie.runningTime)
        description.text = movie.description
    }
}
