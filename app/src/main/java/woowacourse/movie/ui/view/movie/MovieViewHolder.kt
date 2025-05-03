package woowacourse.movie.ui.view.movie

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.setPosterImage

class MovieViewHolder(
    view: View,
    private val onSelectMovieListener: OnSelectMovieListener,
) : RecyclerView.ViewHolder(view) {
    private val poster = view.findViewById<ImageView>(R.id.img_poster)
    private val title = view.findViewById<TextView>(R.id.tv_movie_title)
    private val screeningDate = view.findViewById<TextView>(R.id.tv_movie_screening_date)
    private val runningTime = view.findViewById<TextView>(R.id.tv_movie_running_time)
    private val reserveButton = view.findViewById<Button>(R.id.btn_reserve)

    fun bind(movieUiModel: MovieUiModel) {
        poster.setPosterImage(movieUiModel.poster)
        title.text = movieUiModel.title
        val screeningStartDate = movieUiModel.screeningStartDate
        val screeningEndDate = movieUiModel.screeningEndDate
        screeningDate.text =
            itemView.context.getString(
                R.string.screening_date_period, screeningStartDate, screeningEndDate,
            )
        runningTime.text =
            itemView.context.getString(R.string.minute_text, movieUiModel.runningTime)

        reserveButton.setOnClickListener {
            onSelectMovieListener.onSelect(movieUiModel)
        }
    }
}
